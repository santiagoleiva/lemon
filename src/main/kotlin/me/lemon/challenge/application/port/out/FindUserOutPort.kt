package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.User

interface FindUserOutPort {

    fun by(id: Int): User

}
