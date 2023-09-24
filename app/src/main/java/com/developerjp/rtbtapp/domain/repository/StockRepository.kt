package com.developerjp.rtbtapp.domain.repository

import com.developerjp.rtbtapp.domain.model.IntradayInfo
import com.developerjp.rtbtapp.domain.model.RtBtInfo
import com.developerjp.rtbtapp.domain.model.RtBtListing
import com.developerjp.rtbtapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface StockRepository {

    suspend fun getRtBtListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<RtBtListing>>>

    suspend fun getIntradayInfo(
        name: String
    ): Resource<List<IntradayInfo>>

    suspend fun getRtBtInfo(
       name: String
    ): Resource<RtBtInfo>
}