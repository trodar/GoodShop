package com.trodar.model

import com.trodar.model.validation.validEmail
import com.trodar.model.validation.validName
import com.trodar.model.validation.validPhone


data class Profile (
    val photoUrl: String? = null,
    val user: String = "",
    val phone: String = "",
    val email: String = "",
    val advt: Boolean = true,
) {
    val isRegisterValid = validName(user) && validEmail(email) && validPhone(phone)
    val isLoginValid = validPhone(phone)
}