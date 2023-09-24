package com.developerjp.rtbtapp.data.repository

import com.developerjp.rtbtapp.data.csv.CSVParser
import com.developerjp.rtbtapp.data.remote.RtBtApi
import com.developerjp.rtbtapp.data.local.StockDatabase
import com.developerjp.rtbtapp.data.mapper.toRtBtInfo
import com.developerjp.rtbtapp.data.mapper.toRtBtListing
import com.developerjp.rtbtapp.data.mapper.toRtBtListingEntity
import com.developerjp.rtbtapp.domain.model.IntradayInfo
import com.developerjp.rtbtapp.domain.model.RtBtInfo
import com.developerjp.rtbtapp.domain.model.RtBtListing
import com.developerjp.rtbtapp.domain.repository.StockRepository
import com.developerjp.rtbtapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: RtBtApi,
    private val db: StockDatabase,
    private val rtbtListingParser: CSVParser<RtBtListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,
): StockRepository{

    private val dao = db.dao

    override suspend fun getRtBtListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<RtBtListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchRtBtListing(query)
            emit(Resource.Success(
                data = localListing.map { it.toRtBtListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListing = try {
                val response = api.getListings()
                //val csvReader = CSVReader(InputStreamReader(response.byteStream()))
                rtbtListingParser.parse(response.byteStream())
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let {  listings ->
                dao.clearRtBtListings()
                dao.insertRtBtListings(
                    listings.map { it.toRtBtListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchRtBtListing("")
                        .map { it.toRtBtListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntradayInfo(name: String): Resource<List<IntradayInfo>> {
        return try{
            val response = api.getIntradayInfo(name)
            val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getRtBtInfo(name: String): Resource<RtBtInfo> {
        return try {
            val result = api.getRtBtInfo(name)
            Resource.Success(result.toRtBtInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load rtbt info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load rtbt info"
            )
        }
    }
}