package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import me.lemon.challenge.domain.User

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserControllerModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String,
    val wallet: List<WalletItemControllerModel>
) {

    companion object {

        fun from(domain: User): UserControllerModel = UserControllerModel(
            id = domain.id!!,
            firstname = domain.firstname,
            lastname = domain.lastname,
            alias = domain.alias,
            email = domain.email,
            wallet = domain.wallet.map { WalletItemControllerModel.from(it) }
        )

    }

}
