package com.oop.quizit.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name="option")
@Data
@AllArgsConstructor
@NoArgsConstructor
data class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val optionId: Long? = null,
    val title: String? = null,
    val correct: Boolean? = null,
    @ManyToOne
    @JoinColumn(nullable = false, name = "question_id")
    val questionId: Question? = null
)

