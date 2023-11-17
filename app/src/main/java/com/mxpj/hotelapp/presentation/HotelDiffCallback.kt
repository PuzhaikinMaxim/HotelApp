package com.mxpj.hotelapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.mxpj.hotelapp.domain.Hotel

class HotelDiffCallback(
    private val oldList: List<Hotel>,
    private val newList: List<Hotel>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}