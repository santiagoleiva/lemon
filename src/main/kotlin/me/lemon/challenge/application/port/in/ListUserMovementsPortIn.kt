package me.lemon.challenge.application.port.`in`

import me.lemon.challenge.domain.Movement

interface ListUserMovementsPortIn {

    fun execute(command: Command): List<Movement>

    data class Command(
        val userId: Int,
        val limit: Int,
        val offset: Int,
        val movementType: String,
        val currency: String
    )

}
