package com.developerjp.rtbtapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RtBtListingEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val status: String,
    val department: String,
    val line: String
)
