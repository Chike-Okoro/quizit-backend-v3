package com.oop.quizit

import com.oop.quizit.model.Role
import com.oop.quizit.model.User
import com.oop.quizit.repository.RoleRepository
import com.oop.quizit.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
@EntityScan(basePackageClasses = [QuizitBackendV3Application::class, Jsr310JpaConverters::class])
class QuizitBackendV3Application(val roleRepository: RoleRepository, val userRepository: UserRepository, val passwordEncoder: PasswordEncoder){

    @PostConstruct
    fun init() = TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

    @PostConstruct
    fun createRole(){

        if(roleRepository.existsByName("PARTICIPANT")!!){
            println("PARTICIPANT ALREADY EXISTS")
        } else {
            val role1 = Role("PARTICIPANT")
            roleRepository.save(role1)
        }

        if(roleRepository.existsByName("ADMIN")!!){
            println("ADMIN ALREADY EXISTS")
        } else {
            val role2 = Role("ADMIN")
            roleRepository.save(role2)
        }

        if (!userRepository.existsByUsername("admin22")){
            val roles: MutableSet<Role> = HashSet()
            val admin1 = roleRepository.findByName("ADMIN")
            if (admin1 != null) {
                roles.add(admin1)
            }
            val pass = passwordEncoder.encode("Admin123$")
//            val user = User("adminuser22", "Admin", "User1", pass, roles)

            val user = User().apply {
                firstName = "Admin"
                lastName = "User1"
                username = "admin22"
                password = pass
            }

            user.roles = roles
            userRepository.save(user)
        }

    }
}

fun main(args: Array<String>) {
    runApplication<QuizitBackendV3Application>(*args)
}
