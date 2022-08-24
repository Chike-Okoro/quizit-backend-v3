package com.oop.quizit.controller

import com.oop.quizit.model.QuizRoom
import com.oop.quizit.model.User
import com.oop.quizit.repository.QuizRoomRepository
import com.oop.quizit.request.CreateQuizRoomRequest
import com.oop.quizit.response.QuizRoomResponse
import com.oop.quizit.service.QuizRoomService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

/*
    @GetMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PARTICIPANT')")
    fun getQuizRoomByName(@PathVariable("name") name: String): QuizRoom{
        return service.getQuizRoomByName(name)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getQuizRoomById(@PathVariable("id") adminId: User): List<QuizRoom>{
        return service.getQuizRoomById(adminId)
    }
*/
}