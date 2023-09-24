package com.developerjp.rtbtapp.presentation.rtbt_listings

import com.developerjp.rtbtapp.domain.model.RtBtListing

data class RtBtListingsState(
    val rtbts: List<RtBtListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
