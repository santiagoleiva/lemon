package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.domain.Currency
import org.springframework.stereotype.Component
import java.util.*

@Component
class CurrencyJdbcAdapter(
    private val currencyJdbcRepository: CurrencyJdbcRepository
) : FindCurrencyPortOut {

    override fun all(): List<Currency> = currencyJdbcRepository
        .findAll()
        .map { it.toDomain() }

    override fun by(code: String): Optional<Currency> = currencyJdbcRepository
        .findByCode(code)
        .map { it.toDomain() }

    private fun CurrencyJdbcModel.toDomain(): Currency = Currency(
        id = this.id,
        code = this.code,
        description = this.description,
        decimals = this.decimals
    )

}
