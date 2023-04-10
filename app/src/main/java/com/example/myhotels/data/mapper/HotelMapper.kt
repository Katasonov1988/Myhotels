package com.example.myhotels.data.mapper

import com.example.myhotels.R
import com.example.myhotels.data.network.model.HotelDetailItemData
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.data.network.model.HotelInfoJsonContainerData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity
import java.util.*

private const val COLON = ":"
internal fun com.example.myhotels.data.network.model.HotelData.toHotelEntity(): HotelEntity {
    return HotelEntity(
        id = id,
        name = name,
        address = address.lowercase(),
        stars = stars.toInt(),
        distance = buildString { R.string.count_of_free_rooms;distance },
        suitesAvailability = suitesAvailability.split(COLON).size.toString()
    )
}

internal fun HotelDetailItemData.toHotelDetailItem(): HotelDetailItem {
    return HotelDetailItem(
        id = id,
        name = name,
        address = address.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
        stars = stars.toInt(),
        distance = distance,
        suitesAvailability = suitesAvailability.split(COLON).size.toString(),
        image = image,
        lat = lat,
        lon = lon
    )
}

class JsonHotelMapper {
    fun mapJsonContainerToListHotelData(jsonContainer: HotelInfoJsonContainerData): List<HotelData> {
        val result = mutableListOf<HotelData>()
        val jsonObject = jsonContainer.json ?: return result
        val
    }

}