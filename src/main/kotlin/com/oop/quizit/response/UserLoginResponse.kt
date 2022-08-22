package com.oop.quizit.response

data class UserLoginResponse(
    val userId: Long?,
    val userName: String?,
    val firstName: String?,
    val lastName: String?,
    val token: String?,
    val tokenType: String = "Bearer"

)
