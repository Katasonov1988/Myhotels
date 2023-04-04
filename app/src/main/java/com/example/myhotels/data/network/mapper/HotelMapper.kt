package com.example.myhotels.data.network.mapper

import com.example.myhotels.data.network.model.HotelDetailItemData
import com.example.myhotels.data.network.model.HotelListData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelList

internal fun HotelListData.toHotelList(): HotelList {
    return HotelList(
        id = id,
        name = name,
        address = address,
        stars = stars,
        distance = distance,
        suitesAvailability = suitesAvailability
    )
}

internal fun HotelDetailItemData.toHotelDetailItem():HotelDetailItem {
    return HotelDetailItem(
        id = id,
        name = name,
        address = address,
        stars = stars,
        distance = distance,
        suitesAvailability = suitesAvailability,
        image = image,
        lat = lat,
        lon = lon
    )
}