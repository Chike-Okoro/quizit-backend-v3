package com.oop.quizit.repository

import com.oop.quizit.model.QuizRoom
import org.springframework.stereotype.Repository


@Repository
class MockQuizRoomRepository : QuizRoomRepository {
    val quizRooms = listOf(
            QuizRoom(quizRoomId = 1,
                    name = "firstQuizRoom",
                    code = "1234",
                    dateCreated = null,
                    description = "Short note for first room",
                    adminId = null),

            QuizRoom(quizRoomId = 2,
                    name = "secondQuizRoom",
                    code = "4321",
                    dateCreated = null,
                    description = "Short note for second room",
                    adminId = null),

            )

    override fun getQuizRooms(): Collection<QuizRoom> {
        return quizRooms
    }
}