package com.group.quizit.controller

import com.oop.quizit.response.UserResponse
import com.oop.quizit.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    fun getAllUsers(): List<UserResponse> = userService.getAllUsers()

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTICIPANT')")
    @GetMapping("/{id}")
    fun getUsersById(@PathVariable("id") userId: Long): UserResponse = userService.getUsersById(userId)
}