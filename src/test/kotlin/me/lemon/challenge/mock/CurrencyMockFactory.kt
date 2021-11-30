package me.lemon.challenge.mock

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import me.lemon.challenge.domain.Currency
import java.util.Collections.singletonList

object CurrencyMockFactory {

    @JvmStatic
    private val defaultId: Int = 1

    @JvmStatic
    private val defaultCode: String = "currency-code-test"

    @JvmStatic
    private val defaultDescription: String = "currency-description-test"

    @JvmStatic
    private val defaultDecimals: Int = 2

    fun sampleCurrency(): Currency = Currency(
        id = defaultId,
        code = defaultCode,
        description = defaultDescription,
        decimals = defaultDecimals
    )

    fun sampleCurrencies(): List<Currency> = singletonList(sampleCurrency())

    fun currenciesJdbc(): List<CurrencyJdbcModel> = listOf(sampleCurrencyJdbc())

    private fun sampleCurrencyJdbc(): CurrencyJdbcModel = CurrencyJdbcModel(
        id = defaultId,
        code = defaultCode,
        description = defaultDescription,
        decimals = defaultDecimals
    )

}
