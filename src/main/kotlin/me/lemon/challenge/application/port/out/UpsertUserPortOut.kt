package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.CreateUserCommand
import me.lemon.challenge.domain.User

interface UpsertUserPortOut {

    fun create(createUserCommand: CreateUserCommand): User

}
