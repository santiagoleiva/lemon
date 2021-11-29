package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.application.port.out.QueryCurrencyPortOut
import me.lemon.challenge.domain.Currency
import org.springframework.stereotype.Component

@Component
class CurrencyJdbcAdapter(
    private val currencyJdbcRepository: CurrencyJdbcRepository
) : QueryCurrencyPortOut {

    override fun query(): List<Currency> = TODO("Not yet implemented")

}
