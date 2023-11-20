package com.mxpj.hotelapp.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.hotelapp.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val bookingRepository: BookingRepository
): ViewModel() {

    private val _booking = MutableLiveData<Booking>()
    val booking: LiveData<Booking>
        get() = _booking

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean>
        get() = _emailError

    private val _phoneError = MutableLiveData<Boolean>()
    val phoneError: LiveData<Boolean>
        get() = _phoneError

    private val _tourists = MutableLiveData<List<Tourist>>()
    val tourists: LiveData<List<Tourist>>
        get() = _tourists

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    private val _shouldCloseScreen = MutableLiveData(false)
    val shouldCloseScreen: LiveData<Boolean>
        get() = _shouldCloseScreen

    private var currentTouristNumber = 1

    init {
        viewModelScope.launch {
            val newBooking = bookingRepository.getBooking()
            newBooking?.let {
                _booking.postValue(it)
            }
        }
        _tourists.value = listOf(
            Tourist(currentTouristNumber,
                getTouristNumber(),
                false,
                TouristData()
            ))
    }

    fun addTourist() {
        currentTouristNumber++
        if(currentTouristNumber > maxTouristNumber){
            return
        }
        val touristListCopy = _tourists.value?.map { it.copy() }?.toMutableList() ?: mutableListOf()
        touristListCopy.add(Tourist(currentTouristNumber, getTouristNumber(),false, TouristData()))
        _tourists.value = touristListCopy
    }

    fun changeTouristCardVisibility(id: Int) {
        val touristListCopy = _tourists.value?.map {
            if(it.id == id){
                it.copy(isHidden = !it.isHidden)
            }else{
                it.copy()
            }
        }?.toMutableList() ?: mutableListOf()
        _tourists.value = touristListCopy
    }

    fun resetShouldCloseScreen() {
        _shouldCloseScreen.value = false
    }

    fun setTouristData(id: Int, touristData: TouristData) {
        val tourist = _tourists.value?.find {
            id == it.id
        }
        if (tourist != null) {
            tourist.touristData = touristData
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun makePayment(email: String) {
        var isEveryFieldValid = true
        val touristListCopy = _tourists.value?.map {
            val validationResult = validate(it.touristData)
            if(isEveryFieldValid){
                isEveryFieldValid = isEveryFieldValid(validationResult)
            }
            it.copy(touristDataValidity = validationResult)
        } ?: listOf()
        _tourists.value = touristListCopy
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPhoneValid = _phoneNumber.value?.length == 10
        if(!isEmailValid) {
            _emailError.value = true
        }
        if(!isPhoneValid) {
            _phoneError.value = true
        }
        _shouldCloseScreen.value = isEveryFieldValid && isEmailValid && isPhoneValid
    }

    private fun validate(touristData: TouristData): TouristDataValidity {
        val nameValidity = touristData.name.isNotEmpty()
        val surnameValidity = touristData.name.isNotEmpty()
        val dateOfBirthValidity = touristData.name.isNotEmpty()
        val citizenshipValidity = touristData.name.isNotEmpty()
        val passportNumberValidity = touristData.name.isNotEmpty()
        val passportExpirationValidity = touristData.name.isNotEmpty()
        return TouristDataValidity(
            nameValidity,
            surnameValidity,
            dateOfBirthValidity,
            citizenshipValidity,
            passportNumberValidity,
            passportExpirationValidity
        )
    }

    private fun isEveryFieldValid(touristDataValidity: TouristDataValidity): Boolean {
        val fields: List<Boolean>
        with(touristDataValidity){
            fields = listOf(
                isNameValid,
                isSurnameValid,
                isCitizenshipValid,
                isDateOfBirthValid,
                isPassportNumberValid,
                isPassportExpirationValid
            )
        }
        fields.forEach { if(!it) return false }
        return true
    }

    private fun getTouristNumber(): String {
        return touristNumbers[currentTouristNumber] ?: ""
    }

    fun resetEmailError() {
        _emailError.value = false
    }

    fun resetPhoneError() {
        _phoneError.value = false
    }

    fun checkForEmailError(email: String) {
        val isNotMatches = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _emailError.value = isNotMatches
    }

    fun resetFieldError(id: Int, field: TouristField) {
        val touristListCopy = _tourists.value?.map {
            if(it.id == id){
                it.copy(touristDataValidity = field.getNewValidity(it.touristDataValidity))
            }else{
                it.copy()
            }
        } ?: listOf()
        _tourists.value = touristListCopy
    }

    enum class TouristField(
        val getNewValidity: (TouristDataValidity) -> TouristDataValidity,
        val getValidity: (TouristDataValidity) -> Boolean
    ) {
        NAME({ it.copy(isNameValid = true) }, { it.isNameValid }),
        SURNAME({ it.copy(isSurnameValid = true) }, { it.isSurnameValid }),
        CITIZENSHIP({ it.copy(isCitizenshipValid = true) }, { it.isCitizenshipValid }),
        DATE_OF_BIRTH({ it.copy(isDateOfBirthValid = true) }, { it.isDateOfBirthValid }),
        PASSPORT({ it.copy(isPassportNumberValid = true) }, { it.isPassportNumberValid }),
        PASSPORT_EXPIRATION({ it.copy(isPassportExpirationValid = true) }, { it.isPassportExpirationValid })
    }

    companion object {
        private val touristNumbers = mapOf(
            1 to "Первый",
            2 to "Второй",
            3 to "Третий",
            4 to "Четвертый",
            5 to "Пятый",
        )
        private const val maxTouristNumber = 5
    }
}