package com.mxpj.hotelapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.mxpj.hotelapp.databinding.FragmentBookingBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener

class BookingFragment: BaseFragment<FragmentBookingBinding>(FragmentBookingBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.etPhone.addSuffix("nig")
        val listener = MaskedTextChangedListener.installOn(
            binding.etPhone,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    binding.etPhone.suffix = mask.substring(formattedValue.length)
                }
            }
        )
    }

    companion object {
        private const val mask = "+7 (***) ***-**-**"
    }
}