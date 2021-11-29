package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class CreateUserUseCase(
    private val userJdbcAdapter: UpsertUserPortOut
) : CreateUserPortIn {

    override fun execute(command: CreateUserPortIn.Command): User {
        val user = command.toDomain()
        return userJdbcAdapter.create(user)
    }

    private fun CreateUserPortIn.Command.toDomain(): User = User(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email
    )

}
