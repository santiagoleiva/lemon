package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.User

interface UpsertUserPortOut {

    fun create(user: User): User

}
