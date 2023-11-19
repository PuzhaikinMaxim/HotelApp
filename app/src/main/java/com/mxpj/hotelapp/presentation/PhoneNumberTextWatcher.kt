package com.mxpj.hotelapp.presentation

import android.text.Editable
import android.text.TextWatcher
import javax.inject.Inject

class PhoneNumberTextWatcher @Inject constructor(): TextWatcher {

    //private val symbols: List<Char> =

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        println(p0)
        println(p1)
        println(p3)
    }

    override fun afterTextChanged(p0: Editable?) {
        //p0?.append("5")
        //p0?.append("5")

        //println(p0)
    }
}