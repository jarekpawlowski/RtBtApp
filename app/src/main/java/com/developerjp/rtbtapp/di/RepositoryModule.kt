package com.developerjp.rtbtapp.di

import com.developerjp.rtbtapp.data.csv.CSVParser
import com.developerjp.rtbtapp.data.csv.IntradayInfoParser
import com.developerjp.rtbtapp.data.csv.RtBtListngsParser
import com.developerjp.rtbtapp.data.repository.StockRepositoryImpl
import com.developerjp.rtbtapp.domain.model.IntradayInfo
import com.developerjp.rtbtapp.domain.model.RtBtListing
import com.developerjp.rtbtapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRtBtListingsParser(
        rtBtListingsParser: RtBtListngsParser
    ): CSVParser<RtBtListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        IntradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}