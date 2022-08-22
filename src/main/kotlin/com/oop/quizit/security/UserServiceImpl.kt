package com.oop.quizit.security

import com.oop.quizit.model.Role
import com.oop.quizit.model.User
import com.oop.quizit.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service(value = "userAuthService")
class UserServiceImpl : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository!!.findByUsername(username)
            ?: throw UsernameNotFoundException("Invalid username or password.")
        return org.springframework.security.core.userdetails.User(user.username, user.password, getAuthority(user))
    }

    private fun getAuthority(user: User): Set<SimpleGrantedAuthority> {
        val authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
        user.roles.forEach(Consumer { role: Role -> authorities.add(SimpleGrantedAuthority("ROLE_" + role.name)) })
        return authorities
    }

    fun findOne(username: String?): User? {
        return userRepository!!.findByUsername(username!!)
    }
}