package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import me.lemon.challenge.domain.Movement

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MovementControllerModel(
    val currency: String,
    val type: String,
    val amount: String
) {

    companion object {

        fun from(domain: Movement): MovementControllerModel = MovementControllerModel(
            currency = domain.currency.code,
            type = domain.type.name,
            amount = domain.amount.setScale(domain.currency.decimals).toPlainString()
        )

    }

}
