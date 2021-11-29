package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.UserJdbcModel
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class UserJdbcAdapter(
    private val userJdbcRepository: CrudRepository<UserJdbcModel, Int>
) : UpsertUserPortOut {

    override fun create(user: User): User = user
        .let { it.toJdbcModel() }
        .let { userJdbcRepository.save(it) }
        .let { jdbcModel -> jdbcModel.toDomain() }

    private fun User.toJdbcModel(): UserJdbcModel = UserJdbcModel(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email
    )

    private fun UserJdbcModel.toDomain(): User = User(
        id = this.id!!,
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email
    )

}
