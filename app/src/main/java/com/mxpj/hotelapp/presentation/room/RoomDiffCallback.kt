package com.mxpj.hotelapp.presentation.room

import androidx.recyclerview.widget.DiffUtil
import com.mxpj.domain.room.Room

class RoomDiffCallback(
    private val oldList: List<Room>,
    private val newList: List<Room>
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