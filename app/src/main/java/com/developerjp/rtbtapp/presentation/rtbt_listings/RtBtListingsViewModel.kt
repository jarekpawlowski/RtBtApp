package com.developerjp.rtbtapp.presentation.rtbt_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.developerjp.rtbtapp.domain.repository.StockRepository
import com.developerjp.rtbtapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RtBtListingsViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(RtBtListingsState())

    private var searchJob: Job? = null

    init {
        getRtBtListings()
    }
    fun onEvent(event: RtBtListingsEvent) {
        when(event){
            is RtBtListingsEvent.Refresh -> {
                getRtBtListings(fetchFromRemote = true)
            }
            is RtBtListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getRtBtListings()
                }
            }
        }
    }

    private fun getRtBtListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getRtBtListings(fetchFromRemote, query)
                .collect {result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let {listings ->
                                state = state.copy(
                                    rtbts = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }

            }
        }
    }
}