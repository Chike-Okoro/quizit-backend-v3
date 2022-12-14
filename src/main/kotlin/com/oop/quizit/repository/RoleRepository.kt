package com.oop.quizit.repository

import com.oop.quizit.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?

    fun existsByName(name: String): Boolean?
}