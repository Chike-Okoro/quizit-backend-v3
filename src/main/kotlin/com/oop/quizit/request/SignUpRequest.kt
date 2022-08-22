package com.oop.quizit.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignUpRequest {
    @NotBlank
    @Size(min = 2, max = 40)
    lateinit var firstName: String

    @NotBlank
    @Size(min = 2, max = 40)
    lateinit var lastName: String

    @NotBlank
    @Size(min = 3, max = 15)
    lateinit var username: String

    @NotBlank
    @Size(min=6, max = 20)
    lateinit var password:String
}
