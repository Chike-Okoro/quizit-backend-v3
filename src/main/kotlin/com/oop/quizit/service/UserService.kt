package com.oop.quizit.service

import com.oop.quizit.exception.UserNotFoundException
import com.oop.quizit.model.User
import com.oop.quizit.repository.RoleRepository
import com.oop.quizit.repository.UserRepository
import com.oop.quizit.response.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional
class UserService(val userRepository: UserRepository,
                  val roleRepository: RoleRepository
) {

    fun getAllUsers(): List<UserResponse> {
        val user: List<User> = userRepository.findAll()
        return user.stream().map(this::fromUserEntityToUserResponse).collect(Collectors.toList())
    }

    fun getUsersById(userId: Long): UserResponse {
        val user: User = userRepository.findById(userId).orElseThrow {
            UserNotFoundException(HttpStatus.NOT_FOUND, "No matching user was found")
        }
        return UserResponse(user.id, user.username, user.firstName, user.lastName, user.roles)
    }

    fun fromUserEntityToUserResponse(user: User): UserResponse {
        return UserResponse(user.id, user.username, user.firstName, user.lastName, user.roles)
    }

}