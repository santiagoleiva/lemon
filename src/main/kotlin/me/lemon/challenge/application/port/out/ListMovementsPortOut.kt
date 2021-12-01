package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Movement

interface ListMovementsPortOut {

    fun by(user: Int, type: String, currency: String, limit: Int, offset: Int): List<Movement>

}
