package com.mxpj.hotelapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.ImageSliderContainerBinding
import com.squareup.picasso.Picasso

class ImageSliderAdapter(): Adapter<ImageSliderAdapter.ImageViewHolder>() {

    var imageUrlList = listOf<String>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageSliderContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageUrlList[position]
        with(holder.binding.ivSliderImage) {
            Picasso.get().load(item).into(this)
        }
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }

    class ImageViewHolder(val binding: ImageSliderContainerBinding): ViewHolder(binding.root)
}