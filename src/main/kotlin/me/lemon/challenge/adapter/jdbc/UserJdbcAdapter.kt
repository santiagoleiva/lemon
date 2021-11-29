package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.UserJdbcModel
import me.lemon.challenge.adapter.jdbc.model.WalletReferenceJdbcModel
import me.lemon.challenge.application.port.out.ExistsUserPortOut
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.domain.Balance
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class UserJdbcAdapter(
    private val userJdbcRepository: UserJdbcRepository
) : UpsertUserPortOut, ExistsUserPortOut {

    override fun create(user: User): User = user
        .toJdbcModel()
        .let { userJdbcRepository.save(it) }
        .let { user.copy(id = it.id) }

    override fun byAlias(alias: String): Boolean = userJdbcRepository.existsByAlias(alias)

    override fun byEmail(email: String): Boolean = userJdbcRepository.existsByEmail(email)

    private fun User.toJdbcModel(): UserJdbcModel = UserJdbcModel(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email,
        wallet = this.wallet.toWalletReference()
    )

    private fun List<Balance>.toWalletReference(): Set<WalletReferenceJdbcModel> = this
        .map { it.toWalletReferenceModel() }
        .toSet()

    private fun Balance.toWalletReferenceModel(): WalletReferenceJdbcModel = WalletReferenceJdbcModel(
        currencyId = this.currency.id,
        balance = this.amount
    )

}
