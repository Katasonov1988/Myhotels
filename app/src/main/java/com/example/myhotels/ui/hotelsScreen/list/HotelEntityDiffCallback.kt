package com.example.myhotels.ui.hotelsScreen.list

import androidx.recyclerview.widget.DiffUtil
import com.example.myhotels.domain.model.HotelEntity

object HotelEntityDiffCallback : DiffUtil.ItemCallback<HotelEntity>() {
    override fun areItemsTheSame(oldItem: HotelEntity, newItem: HotelEntity): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: HotelEntity, newItem: HotelEntity): Boolean {
        return oldItem == newItem
    }
}