package me.lemon.challenge.application.port.`in`

import me.lemon.challenge.domain.CreateUserCommand
import me.lemon.challenge.domain.User

interface CreateUserPortIn {

    fun execute(command: CreateUserCommand): User

}
