package com.mxpj.hotelapp.presentation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.textfield.TextInputEditText
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.ItemTouristBinding
import com.mxpj.hotelapp.domain.Tourist
import com.mxpj.hotelapp.domain.TouristData

class TouristAdapter(
    private val context: Context
): RecyclerView.Adapter<TouristAdapter.TouristViewHolder>() {

    var tourists = listOf<Tourist>()
        set(value) {
            val diffCallback = TouristDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onShowButtonClick: (Int) -> Unit = {}

    var onTouristDataChange: (Int, TouristData) -> Unit = { _,_ -> }

    var onTextChange: (Int, BookingViewModel.TouristField) -> Unit = {_,_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val binding = ItemTouristBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val item = tourists[position]
        with(holder.binding){
            tvTouristNumber.text = context.getString(R.string.tourist_number, item.touristNumber)
            val fieldsList = mapOf(
                BookingViewModel.TouristField.NAME to etName,
                BookingViewModel.TouristField.SURNAME to etSurname,
                BookingViewModel.TouristField.DATE_OF_BIRTH to etDateOfBirth,
                BookingViewModel.TouristField.CITIZENSHIP to etCitizenship,
                BookingViewModel.TouristField.PASSPORT to etPassport,
                BookingViewModel.TouristField.PASSPORT_EXPIRATION to etPassportExpiration
            )
            if(item.isHidden){
                llHideableFieldsContainer.visibility = View.GONE
                ivShowArrow.rotationX = 180f
            }
            else{
                llHideableFieldsContainer.visibility = View.VISIBLE
                ivShowArrow.rotationX = 0f
            }
            btnShowButton.setOnClickListener {
                onShowButtonClick(item.id)
            }
            fieldsList.forEach {
                val isValid = it.key.getValidity(item.touristDataValidity)
                if(isValid){
                    it.value.setBackgroundColor(ContextCompat.getColor(context, R.color.edit_text_background))
                } else{
                    it.value.setBackgroundColor(ContextCompat.getColor(context, R.color.error))
                }
                addOnChangeListener(item.id, it.value, this, it.key)
            }
        }
    }

    override fun getItemCount(): Int {
        return tourists.size
    }

    private fun addOnChangeListener(
        id: Int,
        et: TextInputEditText,
        binding: ItemTouristBinding,
        field: BookingViewModel.TouristField
    ) {
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onTextChange(id, field)
            }

            override fun afterTextChanged(p0: Editable?) {
                onTouristDataChange(id, getTouristData(binding))
            }
        })
    }

    private fun getTouristData(binding: ItemTouristBinding): TouristData {
        return TouristData(
            binding.etName.text.toString(),
            binding.etSurname.text.toString(),
            binding.etDateOfBirth.text.toString(),
            binding.etCitizenship.text.toString(),
            binding.etPassport.text.toString(),
            binding.etPassportExpiration.text.toString()
        )
    }

    class TouristViewHolder(val binding: ItemTouristBinding): ViewHolder(binding.root)
}