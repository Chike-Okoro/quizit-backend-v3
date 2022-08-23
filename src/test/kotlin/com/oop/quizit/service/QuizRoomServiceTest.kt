package com.oop.quizit.service

import com.oop.quizit.repository.QuizRoomRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QuizRoomServiceTest{
    private val quizRoomRepository : QuizRoomRepository = mockk(relaxed = true)

    private val quizRoomService = QuizRoomService(quizRoomRepository)

    @Test
    fun shouldCallRepositoryToRetrieveQuizRooms(){
        //every { quizRoomRepository.getQuizRooms() } returns  emptyList()

        val quizRooms = quizRoomService.fetchQuizRooms()

        verify(exactly = 1) {quizRoomRepository.getQuizRooms()}
    }

}