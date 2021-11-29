package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import me.lemon.challenge.adapter.jdbc.model.UserJdbcModel
import me.lemon.challenge.adapter.jdbc.model.WalletReferenceJdbcModel
import me.lemon.challenge.application.port.out.ExistsUserPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.Balance
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class UserJdbcAdapter(
    private val userJdbcRepository: UserJdbcRepository,
    private val currencyJdbcRepository: CurrencyJdbcRepository
) : UpsertUserPortOut, ExistsUserPortOut, FindUserOutPort {

    override fun create(user: User): User = user
        .toJdbcModel()
        .let { jdbcModel -> userJdbcRepository.save(jdbcModel) }
        .let { jdbcModel -> user.copy(id = jdbcModel.id) }

    override fun byAlias(alias: String): Boolean = userJdbcRepository.existsByAlias(alias)

    override fun byEmail(email: String): Boolean = userJdbcRepository.existsByEmail(email)

    override fun by(id: Int): User = userJdbcRepository
        .findById(id)
        .map { jdbcModel -> jdbcModel.toDomain() }
        .orElseThrow { UserNotFoundException() }

    private fun User.toJdbcModel(): UserJdbcModel = UserJdbcModel(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email,
        wallet = this.wallet.toWalletReference()
    )

    private fun List<Balance>.toWalletReference(): Set<WalletReferenceJdbcModel> = this
        .map { balance -> balance.toWalletReferenceModel() }
        .toSet()

    private fun Balance.toWalletReferenceModel(): WalletReferenceJdbcModel = WalletReferenceJdbcModel(
        currencyId = this.currency.id,
        balance = this.amount
    )

    private fun UserJdbcModel.toDomain(): User = User(
        id = this.id,
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email,
        wallet = this.wallet.toDomain()
    )

    private fun Iterable<WalletReferenceJdbcModel>.toDomain(): MutableList<Balance> {
        val walletReferences = this.associate { it.currencyId to it.balance }
        return currencyJdbcRepository
            .findAllById(walletReferences.keys)
            .map { it.toBalanceWithAmount(walletReferences.getOrDefault(it.id, BigDecimal.ZERO)) }
            .toMutableList()
    }

    private fun CurrencyJdbcModel.toBalanceWithAmount(
        amount: BigDecimal
    ): Balance = Balance(
        currency = this.toDomain(),
        amount = amount
    )

    private fun CurrencyJdbcModel.toDomain(): Currency = Currency(
        id = this.id,
        code = this.code,
        description = this.description,
        decimals = this.decimals
    )

}
