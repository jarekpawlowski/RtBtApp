package com.developerjp.rtbtapp.presentation.rtbt_listings

sealed class RtBtListingsEvent {
    object Refresh: RtBtListingsEvent()
    data class OnSearchQueryChange(val query: String): RtBtListingsEvent()
}
