package com.developerjp.rtbtapp.data.csv

import com.developerjp.rtbtapp.domain.model.RtBtListing
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RtBtListngsParser @Inject constructor(): CSVParser<RtBtListing>{
    override suspend fun parse(stream: InputStream): List<RtBtListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val name = line.getOrNull(0)
                    val status = line.getOrNull(1)
                    val department = line.getOrNull(2)
                    val line = line.getOrNull(3)
                    RtBtListing(
                        name = name?: return@mapNotNull null,
                        status = status?: return@mapNotNull null,
                        department = department?: return@mapNotNull null,
                        line = line?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}