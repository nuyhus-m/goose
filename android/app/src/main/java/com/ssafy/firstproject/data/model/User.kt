package com.ssafy.firstproject.data.model

data class User(
    val username: String,
    val password: String,
    val nickname: String = "",
)
