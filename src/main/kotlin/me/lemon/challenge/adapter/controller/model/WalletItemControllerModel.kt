package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import me.lemon.challenge.domain.Balance

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class WalletItemControllerModel(
    val code: String,
    val amount: String
) {

    companion object {

        fun from(domain: Balance): WalletItemControllerModel = WalletItemControllerModel(
            code = domain.currency.code,
            amount = domain.amount.setScale(domain.currency.decimals).toPlainString()
        )

    }

}
