package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name = "quiz_population")
@Data
@AllArgsConstructor
@NoArgsConstructor
data class QuizPopulation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val quizPopulationId: Long? = null,
    @ManyToOne
    @JoinColumn(nullable = false, name = "participant_id")
    val participantId: User? = null,
    @ManyToOne
    @JoinColumn(nullable = false, name = "quiz_room_id")
    val quizRoomId: QuizRoom? = null

)
