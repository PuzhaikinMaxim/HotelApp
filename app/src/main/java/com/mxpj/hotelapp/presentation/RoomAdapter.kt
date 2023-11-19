package com.mxpj.hotelapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.ItemRoomBinding
import com.mxpj.hotelapp.domain.Room

class RoomAdapter(
    val context: Context
): Adapter<RoomAdapter.ItemRoomBindingViewHolder>() {

    var roomList = listOf<Room>()
        set(value) {
            val diiCallback = RoomDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diiCallback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onSelectRoomCallback: ((Room) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRoomBindingViewHolder {
        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemRoomBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRoomBindingViewHolder, position: Int) {
        val item = roomList[position]
        with(holder.binding){
            setupImageSlider(item.imageUrls, isRoomImages, context)
            tvName.text = item.name
            tvPrice.text = item.price
            tvPricePer.text = item.pricePer
            btnSelectRoom.setOnClickListener {
                onSelectRoomCallback(item)
            }
            setupPeculiarities(item.peculiarities, this)
        }
    }

    private fun setupPeculiarities(peculiarities: List<String>, binding: ItemRoomBinding) {
        peculiarities.reversed().forEach {
            val textView = TextView(
                ContextThemeWrapper(context, R.style.Peculiarity),
                null,
                0
            )
            textView.text = it
            binding.fPeculiarities.addView(textView,0)
            val params = FlexboxLayout.LayoutParams(textView.layoutParams)
            val marginInDp = 10f.getInDip(context)
            params.setMargins(0,marginInDp,marginInDp,0)
            textView.layoutParams = params
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class ItemRoomBindingViewHolder(val binding: ItemRoomBinding): ViewHolder(binding.root)
}