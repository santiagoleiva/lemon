package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import me.lemon.challenge.application.port.out.QueryCurrencyPortOut
import me.lemon.challenge.domain.Currency
import org.springframework.stereotype.Component

@Component
class CurrencyJdbcAdapter(
    private val currencyJdbcRepository: CurrencyJdbcRepository
) : QueryCurrencyPortOut {

    override fun all(): List<Currency> = currencyJdbcRepository
        .findAll()
        .map { it.toDomain() }

    private fun CurrencyJdbcModel.toDomain(): Currency = Currency(
        id = this.id,
        code = this.code,
        description = this.description,
        decimals = this.decimals
    )

}
