package com.oop.quizit.service

import com.oop.quizit.exception.BadRequestException
import com.oop.quizit.model.QuizRoom
import com.oop.quizit.repository.QuizRoomRepository
import com.oop.quizit.repository.UserRepository
import com.oop.quizit.request.CreateQuizRoomRequest
import com.oop.quizit.response.QuizRoomResponse
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class QuizRoomService(val repository: QuizRoomRepository, val userRepository: UserRepository){

    fun createQuizRoom(request: CreateQuizRoomRequest) : QuizRoomResponse {
//        val qname = request.name
//        val qdescription = request.description

        if (request.name?.let { repository.existsByName(it) } == true){
            throw BadRequestException("Name already taken. Try another name")
        }else {
            val qr = QuizRoom().apply {
                name = request.name
                description = request.description
                dateCreated = LocalDate.now()
            }

            var code = generateRandomValue()

            if (repository.existsByCode(code).equals(true)){
                code = generateRandomValue()
            }

            qr.code = code

            var currentUserName: String? = null

            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication !is AnonymousAuthenticationToken) {
                currentUserName = authentication.name
            }

            val user = currentUserName?.let { userRepository.findByUsername(it) }

            qr.adminId = user

            repository.save(qr)

            return QuizRoomResponse(
                qr.quizRoomId, qr.name, qr.code, qr.adminId?.username,
                qr.adminId?.id
            )
        }
    }

    fun generateRandomValue(): String {

        var code = ""
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz!@#$%&"
        val rnd = Random()
        val sb = StringBuilder(6)
        for (i in 0..6) {
            sb.append(chars[rnd.nextInt(chars.length)])
            code = sb.toString()
        }

        return code
    }

}