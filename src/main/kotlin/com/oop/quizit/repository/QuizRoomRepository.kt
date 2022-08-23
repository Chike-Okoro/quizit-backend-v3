package com.oop.quizit.repository

import com.oop.quizit.model.QuizRoom

interface QuizRoomRepository {


    fun getQuizRooms(): Collection<QuizRoom>
}