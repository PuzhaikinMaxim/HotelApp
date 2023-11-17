package com.mxpj.hotelapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.children
import androidx.core.view.marginRight
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout.LayoutParams
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.FragmentHotelBinding
import javax.inject.Inject

class HotelFragment: BaseFragment<FragmentHotelBinding>(FragmentHotelBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HotelViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HotelViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHotelData()
    }

    private fun setupHotelData() {
        viewModel.hotelData.observe(requireActivity()){
            binding.tvName.text = it.name
            binding.tvAddress.text = it.address
            binding.tvRating.text = getString(R.string.rating, it.rating, it.ratingName)
            binding.tvMinimalPrice.text = getString(R.string.minimal_price, it.minimal_price)
            binding.tvPriceForIt.text = it.price_for_it
            binding.tvDescription.text = it.description
            setupPeculiarities(it.peculiarities)
            setupImageSlider(it.imageUrls)
        }
    }

    private fun setupPeculiarities(peculiarities: List<String>) {
        peculiarities.forEach {
            val textView = TextView(
                ContextThemeWrapper(requireActivity(), R.style.Peculiarity),
                null,
                0
            )
            textView.text = it
            binding.fPeculiarities.addView(textView)
            val params = LayoutParams(textView.layoutParams)
            params.setMargins(0,10,10,0)
            textView.layoutParams = params
        }
    }

    private fun setupImageSlider(imageUrls: List<String>) {
        val adapter = ImageSliderAdapter()
        adapter.imageUrlList = imageUrls
        binding.isHotelImages.vpImageSlider.adapter = adapter
    }
}