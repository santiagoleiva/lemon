package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.User
import java.util.*

interface FindUserOutPort {

    fun by(id: Int): Optional<User>

}
