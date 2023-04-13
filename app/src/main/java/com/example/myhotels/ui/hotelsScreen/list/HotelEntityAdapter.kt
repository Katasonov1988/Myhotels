package com.example.myhotels.ui.hotelsScreen.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myhotels.R
import com.example.myhotels.databinding.HotelItemBinding
import com.example.myhotels.domain.model.HotelEntity

class HotelEntityAdapter(
    private val context: Context
) : ListAdapter<HotelEntity, HotelEntityViewHolder>(HotelEntityDiffCallback) {

    var onHotelClickListener: OnHotelClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelEntityViewHolder {
        val binding = HotelItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotelEntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelEntityViewHolder, position: Int) {
        val hotel = getItem(position)
        with(holder.binding) {
            val resources = holder.itemView.resources
            with(hotel) {
                hotelName.text = name
                hotelAddress.text = address
                hotelSuitesAvailability.text =
                    resources.getString(R.string.count_of_free_rooms, suitesAvailability)
                distanceFromCenter.text =
                    resources.getString(R.string.distance_from_center, distance)


                root.setOnClickListener {
                    onHotelClickListener?.onHotelClick(this)
                }
            }
        }
    }

    interface OnHotelClickListener {
        fun onHotelClick(hotelInfo: HotelEntity)
    }
}