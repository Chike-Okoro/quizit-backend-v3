package com.oop.quizit.controller

import com.oop.quizit.model.QuizRoom
import com.oop.quizit.service.QuizRoomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quizroom")
class QuizRoomController(private val service: QuizRoomService) {

    @GetMapping
    fun getQuizRooms(): Collection<QuizRoom> = service.fetchQuizRooms()

}