package me.lemon.challenge.application.port.`in`

import me.lemon.challenge.domain.Movement
import java.math.BigDecimal

interface RegisterMovementPortIn {

    fun execute(command: Command): Movement

    data class Command(
        val userId: Int,
        val currencyCode: String,
        val movementTypeCode: String,
        val amount: BigDecimal
    )

}
