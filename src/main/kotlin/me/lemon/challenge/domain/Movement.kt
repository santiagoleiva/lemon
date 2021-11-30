package me.lemon.challenge.domain

import java.math.BigDecimal

data class Movement(
    val user: User,
    val currency: Currency,
    val type: MovementType,
    val amount: BigDecimal,
    val previousBalance: BigDecimal
)
