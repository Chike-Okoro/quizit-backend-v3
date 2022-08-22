package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="quiz_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
data class QuizRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val quizRoomId: Long? = null,
    val name: String? = null,
    @Column(unique = true)
    val code: String? = null,
    val dateCreated: LocalDate? = null,
    val description: String? = null,
    @ManyToOne
    var adminId: User? = null
)
