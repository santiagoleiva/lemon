package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Movement
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.domain.User

interface ListMovementsPortOut {

    fun by(user: User, type: MovementType, currency: Currency, limit: Int, offset: Int): List<Movement>

}
