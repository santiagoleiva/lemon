package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.application.port.out.UpdateBalancePortOut
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.User
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class BalanceJdbcAdapter(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : UpdateBalancePortOut {

    override fun by(user: User, currency: Currency, amount: BigDecimal) {
        val sqlParameterSource = MapSqlParameterSource()
            .addValue(userIdParam, user.id)
            .addValue(currencyIdParam, currency.id)
            .addValue(balanceParam, amount)

        jdbcTemplate.update(sqlUpdateBalance, sqlParameterSource)
    }

    companion object {

        private const val userIdParam: String = "userId"
        private const val currencyIdParam: String = "currencyId"
        private const val balanceParam: String = "balance"

        @JvmStatic
        private val sqlUpdateBalance: String = """
              update lemon.user_wallet
              set balance = :$balanceParam
              where user_id = :$userIdParam and currency_id = :$currencyIdParam
        """.trimIndent()

    }

}
