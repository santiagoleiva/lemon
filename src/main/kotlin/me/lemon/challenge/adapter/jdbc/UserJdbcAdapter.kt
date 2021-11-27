package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.domain.CreateUserCommand
import me.lemon.challenge.domain.User
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class UserJdbcAdapter(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): UpsertUserPortOut {

    override fun create(createUserCommand: CreateUserCommand): User = TODO("Not yet implemented")

}
