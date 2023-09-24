package com.developerjp.rtbtapp.presentation.rtbt_info

import com.developerjp.rtbtapp.domain.model.IntradayInfo
import com.developerjp.rtbtapp.domain.model.RtBtInfo

data class RtBtInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val rtbt: RtBtInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)