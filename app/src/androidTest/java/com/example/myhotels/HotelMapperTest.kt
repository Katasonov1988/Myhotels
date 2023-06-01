package com.example.myhotels

import com.example.myhotels.data.mapper.HotelMapper
import com.example.myhotels.data.mapper.toHotelDetailItem
import com.example.myhotels.data.network.model.HotelData
import com.example.myhotels.data.network.model.HotelDetailItemData
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class HotelMapperTest {
    @Test
    fun mapHotelDataToHotelEntity_hotelData_hotelEntity() {
        val hotelData = HotelData(
            1,
            "Vova",
            "SPb, Pears street, house 9",
            2.0,
            333.0,
            "2:2:2"
        )

        val mapper = HotelMapper()
        val result = mapper.mapHotelDataToHotelEntity(hotelData)

        assertThat(result.id, `is`(1))
        assertThat(result.name, `is`("Vova"))
        assertThat(result.address, `is`("spb, pears street, house 9"))
        assertThat(result.stars, `is`(2))
        assertThat(result.distance, `is`(333.0))
        assertThat(result.suitesAvailability, `is`(3))

    }

    @Test
    fun hotelDetailItemDataToHotelDetailItem_hotelDetailItemData_hotelDetailItem() {
        val hotelDetailItemData = HotelDetailItemData(
            40611,
            "Belleclaire Hotel",
            "250 West 77th Street, Manhattan",
            3.0,
            100.0,
            "2:2:2",
            "1.jpg",
            40.7826000000000,
            -73.98130000000000
        )
        val hotelDetailItem = hotelDetailItemData.toHotelDetailItem()

        assertThat(hotelDetailItem.id, `is`(40611))
        assertThat(hotelDetailItem.name, `is`("Belleclaire Hotel"))
        assertThat(hotelDetailItem.address, `is`("250 west 77th street, manhattan"))
        assertThat(hotelDetailItem.suitesAvailability, `is`("3"))
        assertThat(
            hotelDetailItem.image,
            `is`("https://github.com/iMofas/ios-android-test/raw/master/1.jpg")
        )
        assertThat(hotelDetailItem.coordinates, `is`(
            "geo:40.7826,-73.9813?q=Belleclaire Hotel")
        )
    }
}
