package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import me.lemon.challenge.domain.Movement
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MovementControllerModel(
    val id: Long? = null,
    val currency: String,
    val type: String,
    val amount: String,
    val createdAt: LocalDateTime
) {

    companion object {

        fun from(domain: Movement): MovementControllerModel = MovementControllerModel(
            id = domain.id,
            currency = domain.currency.code,
            type = domain.type.name,
            amount = domain.amount.setScale(domain.currency.decimals).toPlainString(),
            createdAt = domain.createdAt
        )

    }

}
