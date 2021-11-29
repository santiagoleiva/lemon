package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.User

interface QueryUserOutPort {

    fun by(id: Int): User

}
