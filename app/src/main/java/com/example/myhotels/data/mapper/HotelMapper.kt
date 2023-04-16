package com.example.myhotels.data.mapper

import com.example.myhotels.R
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.data.network.model.HotelDetailItemData
import com.example.myhotels.domain.model.HotelDetailItem
import com.example.myhotels.domain.model.HotelEntity
import java.util.*

private const val COLON = ":"
private const val BASE_IMAGE_URL = "https://github.com/iMofas/ios-android-test/raw/master/"

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
    fun mapHotelDataToHotelEntity(hotelData: HotelData) = HotelEntity(
        id = hotelData.id,
        name = hotelData.name,
        address = hotelData.address.lowercase(),
        stars = hotelData.stars.toInt(),
        distance =  hotelData.distance.toString(),
        suitesAvailability = hotelData.suitesAvailability.split(COLON).size.toString()
    )
}
