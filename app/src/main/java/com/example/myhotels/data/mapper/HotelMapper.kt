package com.example.myhotels.data.mapper

import com.example.myhotels.R
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.data.network.model.HotelDetailItemData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity
import java.util.*

private const val COLON = ":"
private const val BASE_IMAGE_URL = "https://github.com/iMofas/ios-android-test/raw/master/"
private const val EMPTY_LINE = ""

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
        distance = distance.toString(),
        suitesAvailability = suitesAvailability.split(COLON).size.toString(),
        image = BASE_IMAGE_URL + image,
        lat = lat,
        lon = lon
    )
}

class HotelMapper {
    fun mapHotelDataToHotelEntity(hotelData:HotelData) = HotelEntity(
        id = hotelData.id,
        name = hotelData.name,
        address = hotelData.address.lowercase(),
        stars = hotelData.stars.toInt(),
        distance = buildString { R.string.count_of_free_rooms;hotelData.distance },
        suitesAvailability = hotelData.suitesAvailability.split(COLON).size.toString()
    )

}
