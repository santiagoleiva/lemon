package me.lemon.challenge.domain

import java.math.BigDecimal

data class Balance(
    val currency: Currency,
    val amount: BigDecimal
)
