package me.lemon.challenge.application.port.`in`

import me.lemon.challenge.domain.User

interface CreateUserPortIn {

    fun execute(command: Command): User

    data class Command(
        val firstname: String,
        val lastname: String,
        val alias: String,
        val email: String
    )

}
