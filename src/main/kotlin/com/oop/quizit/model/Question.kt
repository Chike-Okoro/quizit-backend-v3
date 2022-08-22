package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name="question")
@Data
@AllArgsConstructor
@NoArgsConstructor
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val questionId: Long? = null,
    val title: String? = null,
    val optionA: String? = null,
    val optionB: String? = null,
    val optionC: String? = null,
    val optionD: String? = null,
    val correctAnswer: String? = null,
    @ManyToOne
    @JoinColumn(nullable = false, name = "quiz_room_id")
    val quizRoomId: QuizRoom? = null
)
