package me.lemon.challenge.mock

import me.lemon.challenge.adapter.jdbc.model.WalletReferenceJdbcModel
import me.lemon.challenge.domain.Balance
import me.lemon.challenge.domain.Currency
import java.math.BigDecimal

object WalletMockFactory {

    @JvmStatic
    private val defaultAmount: BigDecimal = BigDecimal.TEN

    @JvmStatic
    private val defaultCurrency: Currency = CurrencyMockFactory.sampleCurrency()

    fun sampleBalance(): Balance = Balance(
        currency = defaultCurrency,
        amount = defaultAmount
    )

    fun sampleWalletReferenceJdbc(): WalletReferenceJdbcModel = WalletReferenceJdbcModel(
        currencyId = defaultCurrency.id,
        balance = defaultAmount
    )

}
