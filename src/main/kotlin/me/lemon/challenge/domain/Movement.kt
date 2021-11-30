package me.lemon.challenge.domain

import java.math.BigDecimal

data class Movement(
    val user: User,
    val type: MovementType,
    val amount: BigDecimal
)
