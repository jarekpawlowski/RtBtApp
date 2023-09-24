package com.developerjp.rtbtapp.domain.model

import com.squareup.moshi.Json

data class RtBtInfo(
    val name: String,
    val department: String,
    val line: String,
    val machine: String,
    val description: String,
)