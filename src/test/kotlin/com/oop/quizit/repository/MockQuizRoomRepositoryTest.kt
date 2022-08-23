package com.oop.quizit.repository

import org.assertj.core.api.AbstractCollectionAssert
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockQuizRoomRepositoryTest{

    private val mockQuizRoomRepository = MockQuizRoomRepository()

    @Test
    fun provideCollectionOfQuizRooms(){

        val quizRooms = mockQuizRoomRepository.getQuizRooms()

        Assertions.assertThat(quizRooms).isNotEmpty
    }

    @Test
    fun quizRoomDataShouldContainValue(){

        val quizRooms = mockQuizRoomRepository.getQuizRooms()

        Assertions.assertThat(quizRooms).allMatch { it.name!!.isNotBlank() }
    }
}
