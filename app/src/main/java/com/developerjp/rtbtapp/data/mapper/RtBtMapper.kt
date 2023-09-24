package com.developerjp.rtbtapp.data.mapper

import com.developerjp.rtbtapp.data.local.RtBtListingEntity
import com.developerjp.rtbtapp.data.remote.dto.RtBtInfoDto
import com.developerjp.rtbtapp.domain.model.RtBtInfo
import com.developerjp.rtbtapp.domain.model.RtBtListing

fun RtBtListingEntity.toRtBtListing(): RtBtListing{
    return RtBtListing(
        name = name,
        status = status,
        department = department,
        line = line
    )
}

fun RtBtListing.toRtBtListingEntity(): RtBtListingEntity {
    return RtBtListingEntity(
        name = name,
        status = status,
        department = department,
        line = line
    )
}

fun RtBtInfoDto.toRtBtInfo(): RtBtInfo {
    return RtBtInfo(
        name = name ?: "",
        department = department ?: "",
        line = line ?: "",
        machine = machine ?: "",
        description = description ?: ""
    )
}