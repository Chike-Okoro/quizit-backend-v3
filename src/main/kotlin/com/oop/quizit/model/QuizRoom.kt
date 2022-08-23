package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.jetbrains.annotations.NotNull
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
    var name: String? = null,
    @Column(unique = true)
    var code: String? = null,
    var dateCreated: LocalDate? = null,
    var description: String? = null,
    @ManyToOne
    var adminId: User? = null
)
