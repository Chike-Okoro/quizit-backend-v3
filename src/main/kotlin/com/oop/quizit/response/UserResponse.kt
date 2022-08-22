package com.oop.quizit.response

import com.oop.quizit.model.Role

data class UserResponse(
        val userId: Long?,
        val userName: String?,
        val firstName: String?,
        val lastName: String?,
        val role: Set<Role>
)
