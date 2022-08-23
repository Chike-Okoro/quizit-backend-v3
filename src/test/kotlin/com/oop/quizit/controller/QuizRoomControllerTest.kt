package com.oop.quizit.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import org.springframework.web.context.WebApplicationContext



import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class QuizRoomControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnAllQuizRooms() {
        mockMvc.get("/api/quizroom")
                .andDo { print() }
                .andExpect { status { isOk() } }

    }
}