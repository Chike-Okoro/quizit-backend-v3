package com.oop.quizit.service

import com.oop.quizit.model.QuizRoom
import com.oop.quizit.repository.QuizRoomRepository
import org.springframework.stereotype.Service

@Service
class QuizRoomService(private val repository: QuizRoomRepository){

    fun fetchQuizRooms(): Collection<QuizRoom>{

        return repository.getQuizRooms()
    }
}