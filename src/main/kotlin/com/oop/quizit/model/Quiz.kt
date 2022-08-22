package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.Duration
import javax.persistence.*

@Entity
@Table(name="quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
data class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val quizId: Long? = null,
    val timeLimit: Duration? = null,
    val passMark: Double? = null,
    @ManyToOne
    @JoinColumn(nullable = false, name = "quiz_room_id")
    val quizRoomId: QuizRoom? = null
)
