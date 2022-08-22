package com.oop.quizit.request

import javax.validation.constraints.NotBlank

class LoginRequest {
    @NotBlank
    lateinit var username: String

    @NotBlank
    lateinit var password: String
}