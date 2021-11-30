package me.lemon.challenge.mock

import me.lemon.challenge.adapter.jdbc.model.WalletReferenceJdbcModel
import me.lemon.challenge.domain.Balance
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Wallet
import java.math.BigDecimal

object WalletMockFactory {

    @JvmStatic
    private val defaultAmount: BigDecimal = BigDecimal.TEN

    @JvmStatic
    private val defaultCurrency: Currency = CurrencyMockFactory.sampleCurrency()

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

    private fun sampleBalance(): Balance = Balance(
        currency = defaultCurrency,
        amount = defaultAmount
    )

}
