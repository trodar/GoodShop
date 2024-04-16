package com.trodar.model.validation

fun validName(inputText: String): Boolean = inputText.matches(Regex("^([A-Za-z]{2,30})[ ]?([A-Za-z]{0,20})\$"))

fun validEmail(inputText: String): Boolean = inputText.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))

fun validPhone(inputText: String): Boolean = inputText.matches(Regex("[0-9]{10}"))

