package com.mxpj.hotelapp.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.viewpager2.widget.ViewPager2
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.ImageSliderBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

fun setupImageSlider(
    imageUrls: List<String>,
    binding: ImageSliderBinding,
    context: Context
) {
    setupSliderButtons(imageUrls, binding, context)
    //Picasso.get().load(imageUrls[0]).into(binding.backgroundImage)
    setBackground(imageUrls[0], binding)
    val adapter = ImageSliderAdapter()
    adapter.imageUrlList = imageUrls
    binding.vpImageSlider.adapter = adapter
    binding.vpImageSlider.registerOnPageChangeCallback(
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val radioButton = binding.rgImages.getChildAt(position) as RadioButton
                radioButton.isChecked = true
            }
        })
}

private fun setupSliderButtons(
    imageUrls: List<String>,
    binding: ImageSliderBinding,
    context: Context
) {
    binding.rgImages.removeAllViews()
    for(image in imageUrls){
        LayoutInflater.from(context).inflate(
            R.layout.image_slider_radio_button,
            binding.rgImages
        )
    }
}

private fun setBackground(url: String, binding: ImageSliderBinding) {
    Picasso.get().load(url).into(object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmap?.let {
                val config = Bitmap.Config.ARGB_8888
                val backgroundBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, config)
                binding.backgroundImage.setImageBitmap(backgroundBitmap)
                binding.clContainer.requestLayout()
                binding.backgroundImage.requestLayout()
                binding.vpImageSlider.requestLayout()
            }
            //binding.backgroundImage.setImageBitmap(null)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }

    })
}

/*
private fun Float.toDp(context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
}
 */