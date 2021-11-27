package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.domain.CreateUserCommand
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class CreateUserUseCase(
    private val userJdbcAdapter: UpsertUserPortOut
): CreateUserPortIn {

    override fun execute(command: CreateUserCommand): User = command
        .let { userJdbcAdapter.create(it) }

}
