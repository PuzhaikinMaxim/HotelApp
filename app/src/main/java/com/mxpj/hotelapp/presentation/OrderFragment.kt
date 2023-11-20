package com.mxpj.hotelapp.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.FragmentOrderBinding

class OrderFragment: BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOrderNumber()
        setupOnContinueButtonClick()
        setupOnBackButtonClick()
    }

    private fun setupOrderNumber() {
        val order = (10000..19999).random()
        binding.orderNumber.text = getString(R.string.order_number, order)
    }

    private fun setupOnContinueButtonClick() {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_hotelFragment)
        }
    }

    private fun setupOnBackButtonClick() {
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}