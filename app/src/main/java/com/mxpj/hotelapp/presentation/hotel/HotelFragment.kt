package com.mxpj.hotelapp.presentation.hotel

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexboxLayout.LayoutParams
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.FragmentHotelBinding
import com.mxpj.hotelapp.presentation.BaseFragment
import com.mxpj.hotelapp.presentation.ViewModelFactory
import com.mxpj.hotelapp.presentation.getInDip
import com.mxpj.hotelapp.presentation.setupImageSlider
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
        setupToRoomsSelection()
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
            setupImageSlider(it.imageUrls, binding.isHotelImages, requireActivity())
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
            val marginInDp = 10f.getInDip(requireActivity())
            params.setMargins(
                0,
                marginInDp,
                marginInDp,
                0
            )
            textView.layoutParams = params
        }
    }

    private fun setupToRoomsSelection() {
        binding.btnToRoomsSelection.setOnClickListener {
            findNavController().navigate(R.id.action_hotelFragment_to_roomsFragment)
        }
    }
}