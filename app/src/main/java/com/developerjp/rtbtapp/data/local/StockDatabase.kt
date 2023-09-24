package com.developerjp.rtbtapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RtBtListingEntity::class],
    version = 1
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: RtBtDao
}