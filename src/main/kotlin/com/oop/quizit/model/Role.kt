package com.oop.quizit.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @Column(length = 60)
    var name: String? = null

    constructor(name: String) : this() {
        this.name = name
    }
}