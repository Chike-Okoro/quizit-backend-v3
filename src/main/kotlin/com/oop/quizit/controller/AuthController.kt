package com.oop.quizit.controller

import com.oop.quizit.exception.AppException
import com.oop.quizit.model.User
import com.oop.quizit.repository.RoleRepository
import com.oop.quizit.repository.UserRepository
import com.oop.quizit.request.LoginRequest
import com.oop.quizit.request.SignUpRequest
import com.oop.quizit.response.CreateUserResponse
import com.oop.quizit.response.UserLoginResponse
import com.oop.quizit.security.TokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder,
    val tokenProvider: TokenProvider
) {
    @PostMapping("/sign-up")
    fun registerUser(@Valid @RequestBody req: SignUpRequest): CreateUserResponse {
        if (userRepository.existsByUsername(req.username)) {
            return CreateUserResponse(HttpStatus.BAD_REQUEST.name, "Username is already taken")
        }


        // Creating user account
        val user = User().apply {
            firstName = req.firstName
            lastName = req.lastName
            username = req.username
            password = req.password
        }

        user.password = passwordEncoder.encode(user.password)

        val userRole = roleRepository.findByName("PARTICIPANT") ?: throw AppException("User role is not set")

        user.roles = mutableSetOf(userRole)

        userRepository.save(user)



        return CreateUserResponse(HttpStatus.CREATED.name, "User created successfully")
    }

    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<*> {

        val user = userRepository.findByUsername(loginRequest.username) ?: throw AppException("User doesn't exist")

        if (!passwordEncoder.matches(loginRequest.password, user.password)){
            throw AppException("Password is incorrect")
        }

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val generatedToken = tokenProvider.generateToken(authentication)

        val loginResponse = UserLoginResponse(user.id, user.username, user.firstName, user.lastName, generatedToken)

//        return ResponseEntity.ok(JwtAuthenticationResponse(generatedToken))
        return ResponseEntity.ok(loginResponse)
    }
}