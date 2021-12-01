package me.lemon.challenge.mock

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import me.lemon.challenge.adapter.jdbc.model.WalletReferenceJdbcModel
import me.lemon.challenge.domain.*
import java.math.BigDecimal

object WalletMockFactory {

    @JvmStatic
    val defaultMovementType: MovementType = MovementType.DEPOSIT

    @JvmStatic
    val defaultAmount: BigDecimal = BigDecimal.TEN

    @JvmStatic
    private val defaultCurrency: Currency = CurrencyMockFactory.sampleCurrency()

    @JvmStatic
    private val defaultUser: User = UserMockFactory.createdWithId()

    @JvmStatic
    private val defaultMovementId: Long = 1

    fun sampleWallet(): Wallet = Wallet(
        balances = mutableListOf(sampleBalance())
    )

    fun sampleWalletInZero(): Wallet = Wallet(
        balances = mutableListOf(Balance(defaultCurrency, BigDecimal.ZERO))
    )

    fun sampleWalletReferenceJdbc(): WalletReferenceJdbcModel = WalletReferenceJdbcModel(
        currencyId = defaultCurrency.id,
        balance = defaultAmount
    )

    fun sampleWalletReferenceJdbcInZero(): WalletReferenceJdbcModel = WalletReferenceJdbcModel(
        currencyId = defaultCurrency.id,
        balance = BigDecimal.ZERO
    )

    fun sampleMovement(): Movement = Movement(
        user = defaultUser,
        currency = defaultCurrency,
        type = defaultMovementType,
        amount = defaultAmount,
        previousBalance = BigDecimal.ZERO
    )

    fun sampleMovementJdbc(id: Long = defaultMovementId): MovementJdbcModel = sampleMovement()
        .let {
            MovementJdbcModel(
                id = id,
                userId = it.user.id!!,
                currencyId = it.currency.id,
                type = it.type.name,
                amount = it.amount,
                previousBalance = it.previousBalance,
                createdAt = it.createdAt
            )
        }

    private fun sampleBalance(): Balance = Balance(
        currency = defaultCurrency,
        amount = defaultAmount
    )

}
