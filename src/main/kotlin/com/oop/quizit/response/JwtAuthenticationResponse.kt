package com.oop.quizit.response

data class JwtAuthenticationResponse(val accessToken: String) {
    val tokenType = "Bearer"
}