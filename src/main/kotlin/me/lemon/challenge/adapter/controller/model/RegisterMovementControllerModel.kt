package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RegisterMovementControllerModel(
    val currencyCode: String,
    val movementType: String,
    val amount: BigDecimal
)
