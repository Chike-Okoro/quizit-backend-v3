package com.oop.quizit.repository

import com.oop.quizit.model.QuizRoom
import com.oop.quizit.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface QuizRoomRepository : JpaRepository<QuizRoom, Long>{

    fun findByCode(code: String) : QuizRoom

    fun existsByCode(code: String) : Boolean

    fun findAllByAdminId(adminId: User) : List<QuizRoom>

    fun existsByName(name: String) : Boolean
}