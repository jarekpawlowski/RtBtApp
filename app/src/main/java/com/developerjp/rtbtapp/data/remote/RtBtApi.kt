package com.developerjp.rtbtapp.data.remote

import com.developerjp.rtbtapp.data.remote.dto.RtBtInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RtBtApi {
    @GET ("query?function=LISTINGS_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function= TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("name") name:String,
        @Query("apikey") apikey:String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getRtBtInfo(
        @Query("name") name: String,
        @Query("apikey") apikey: String = API_KEY
    ): RtBtInfoDto

    companion object {
        const val API_KEY =""
        const val BASE_URL = "http://192.168.2.53:8080/rtbts/"
    }
}