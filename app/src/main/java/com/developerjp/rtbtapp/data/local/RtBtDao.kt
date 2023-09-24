package com.developerjp.rtbtapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RtBtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRtBtListings(
        RtBtListingEntities: List<RtBtListingEntity>
    )

    @Query("DELETE FROM rtbtlistingentity ")
    suspend fun clearRtBtListings()

    @Query(
        """
        SELECT *
        FROM rtbtlistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == name 
    /*TODO: name change to the proper one*/
        """
    )
    suspend fun searchRtBtListing(query: String): List<RtBtListingEntity>

}