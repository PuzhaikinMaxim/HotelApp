package com.mxpj.hotelapp.domain

data class TouristDataValidity(
    val isNameValid: Boolean = true,
    val isSurnameValid: Boolean = true,
    val isDateOfBirthValid: Boolean = true,
    val isCitizenshipValid: Boolean = true,
    val isPassportNumberValid: Boolean = true,
    val isPassportExpirationValid: Boolean = true
)
