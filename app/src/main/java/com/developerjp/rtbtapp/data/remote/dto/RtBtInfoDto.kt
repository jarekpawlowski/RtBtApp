package com.developerjp.rtbtapp.data.remote.dto

import com.squareup.moshi.Json

data class RtBtInfoDto(
    @field:Json(name="Name") val name: String?,
    @field:Json(name="Department") val department: String?,
    @field:Json(name="Line") val line: String?,
    @field:Json(name="Machine") val machine: String?,
    @field:Json(name="Description") val description: String?,
)