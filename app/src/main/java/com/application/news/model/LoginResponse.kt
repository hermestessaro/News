package com.application.news.model

data class LoginResponse (
    val token: String,
    val code: String,
    val field: String,
    val message: String,

)