package com.mxpj.hotelapp.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.LayoutAnimationController
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mxpj.hotelapp.R
import com.mxpj.hotelapp.databinding.FragmentBookingBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener
import javax.inject.Inject

class BookingFragment: BaseFragment<FragmentBookingBinding>(FragmentBookingBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BookingViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory)[BookingViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBookingData()
        setupPhoneEditText()
        setupEmailEditText()
        setupOnBackButtonClick()
        setupOnMakePaymentButtonClick()
        setupTouristList()
    }

    private fun setupBookingData() {
        viewModel.booking.observe(requireActivity()){
            binding.tvAddress.text = it.hotelAddress
            binding.tvName.text = it.hotelName
            binding.tvDeparture.text = it.departure
            binding.tvTourDates.text = getString(R.string.tour_dates, it.tourDateStart, it.tourDateEnd)
            binding.tvRoom.text = it.room
            binding.tvNutrition.text = it.nutrition
            binding.tvRating.text = getString(R.string.rating, it.rating, it.ratingName)
            binding.tvNumberOfNights.text = it.numberOfNights.toString()
            binding.tvArrivalCountry.text = it.arrivalCountry
            binding.tvHotelName.text = it.hotelName
            binding.tvTourPrice.text = getString(R.string.price, it.tourPrice)
            binding.tvFuelCharge.text = getString(R.string.price, it.fuelCharge)
            binding.tvServiceCharge.text = getString(R.string.price, it.serviceCharge)
            binding.tvTotal.text = getString(R.string.price, it.totalPrice)
            binding.btnMakePayment.text = getString(R.string.make_payment, it.totalPrice)
        }
    }

    private fun setupEmailEditText() {
        binding.etEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                viewModel.resetEmailError()
            } else{
                viewModel.checkForEmailError(binding.etEmail.text.toString())
            }
        }
        viewModel.emailError.observe(requireActivity()){
            if(it) {
                binding.etEmail.setBackgroundColor(ContextCompat.getColor(
                    requireActivity(),
                    R.color.error
                ))
            }else {
                binding.etEmail.setBackgroundColor(ContextCompat.getColor(
                    requireActivity(),
                    R.color.edit_text_background
                ))
            }
        }
    }

    private fun setupPhoneEditText() {
        MaskedTextChangedListener.installOn(
            binding.etPhone,
            formatMask,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    binding.etPhone.suffix = mask.substring(formattedValue.length)
                    viewModel.setPhoneNumber(extractedValue)
                }
            }
        )
        setPhoneTextChangedListener()
        setFocusChangedListener()
        viewModel.phoneError.observe(requireActivity()){
            val colorId = if(it) R.color.error else R.color.edit_text_background
            binding.etPhone.setBackgroundColor(ContextCompat.getColor(requireActivity(), colorId))
        }

    }

    private fun setPhoneTextChangedListener() {
        binding.etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetPhoneError()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun setFocusChangedListener() {
        binding.etPhone.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if(!hasFocus && (binding.etPhone.text?.length ?: 0) == 0){
                binding.etPhone.suffix = ""
                binding.etPhone.invalidate()
            }
            if(hasFocus){
                val textLength = binding.etPhone.text?.length ?: 0
                binding.etPhone.suffix = mask.substring(textLength)
            }
        }
    }

    private fun setupTouristList() {
        val adapter = TouristAdapter(requireActivity())
        viewModel.tourists.observe(requireActivity()){
            adapter.tourists = it
        }
        adapter.onShowButtonClick = {
            viewModel.changeTouristCardVisibility(it)
        }
        adapter.onTouristDataChange = { id, touristData ->
            viewModel.setTouristData(id, touristData)
        }
        adapter.onTextChange = { id, field ->
            viewModel.resetFieldError(id, field)
        }
        val animator = binding.rvTouristList.itemAnimator as SimpleItemAnimator
        animator.supportsChangeAnimations = false
        binding.rvTouristList.adapter = adapter
        binding.rvTouristList.setItemViewCacheSize(7)
        binding.bcAddTourist.setOnClickListener {
            viewModel.addTourist()
        }
    }

    private fun setupOnBackButtonClick() {
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupOnMakePaymentButtonClick() {
        binding.btnMakePayment.setOnClickListener {
            viewModel.makePayment(binding.etEmail.text.toString())
        }
        viewModel.shouldCloseScreen.observe(requireActivity()){
            if(it){
                findNavController().navigate(R.id.action_bookingFragment_to_orderFragment)
                viewModel.resetShouldCloseScreen()
            }
        }
    }

    companion object {
        private const val mask = "+7 (***) ***-**-**"
        private const val formatMask = "+7 ([000]) [000]-[00]-[00]"
    }
}