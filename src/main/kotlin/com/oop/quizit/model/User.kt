package com.oop.quizit.model


import com.oop.quizit.model.Role
import org.hibernate.annotations.NaturalId
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(
    name = "app_user", uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"])]
)
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank
    @Size(max = 40)
    lateinit var firstName: String

    @NotBlank
    @Size(max = 40)
    lateinit var lastName: String

    @NaturalId
    @NotBlank
    @Size(max = 15)
    lateinit var username: String

    @NotBlank
    @Size(max = 100)
    lateinit var password: String

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles = mutableSetOf<Role>()
}