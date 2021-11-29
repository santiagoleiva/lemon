package me.lemon.challenge.application.port.`in`

import me.lemon.challenge.domain.User

interface GetUserByIdPortIn {

    fun execute(id: Int): User

}
