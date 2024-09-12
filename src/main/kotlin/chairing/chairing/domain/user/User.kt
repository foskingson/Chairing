package chairing.chairing.domain.user

import jakarta.persistence.*


class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Int = 0,

    @Column(name = "username", nullable = false, unique = true)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: Role,

//    @ManyToOne
//    @JoinColumn(name = "institution_id")
//    val institution: Institution? = null
)