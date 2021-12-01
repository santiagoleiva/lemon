package me.lemon.challenge.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Movement(
    val id: Long? = null,
    val user: User,
    val currency: Currency,
    val type: MovementType,
    val amount: BigDecimal,
    val previousBalance: BigDecimal,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun with(
            user: User,
            currency: Currency,
            type: MovementType,
            movementAmount: BigDecimal,
            previousBalance: BigDecimal
        ): Movement = Movement(
            user = user,
            currency = currency,
            type = type,
            amount = movementAmount,
            previousBalance = previousBalance
        )
    }
}
