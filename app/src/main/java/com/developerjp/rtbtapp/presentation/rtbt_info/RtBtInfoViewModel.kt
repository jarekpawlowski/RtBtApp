package com.developerjp.rtbtapp.presentation.rtbt_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerjp.rtbtapp.domain.repository.StockRepository
import com.developerjp.rtbtapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RtBtInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(RtBtInfoState())

    init {
        viewModelScope.launch{
            val name = savedStateHandle.get<String>("name") ?: return@launch
            state = state.copy(isLoading = true)
            val rtbtInfoResult = async { repository.getRtBtInfo(name) }
            val intradayInfoResult = async { repository.getIntradayInfo(name) }
            when(val result = rtbtInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        rtbt = result.data,
                        isLoading = false,
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        rtbt = null
                    )
                }
                else -> Unit
            }
            when(val result = intradayInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        stockInfos = result.data ?: emptyList(),
                        isLoading = false,
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        rtbt = null
                    )
                }
                else -> Unit
            }
        }
    }
}