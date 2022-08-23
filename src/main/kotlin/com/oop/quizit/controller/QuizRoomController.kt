package com.oop.quizit.controller

import com.oop.quizit.request.CreateQuizRoomRequest
import com.oop.quizit.response.QuizRoomResponse
import com.oop.quizit.service.QuizRoomService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quizroom")
class QuizRoomController(private val service: QuizRoomService) {

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    fun createQuizRoom(@RequestBody createQuizRoomRequest: CreateQuizRoomRequest): QuizRoomResponse {
        return service.createQuizRoom(createQuizRoomRequest)
    }

}