package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import java.math.BigDecimal

@DisplayName("Balance Jdbc Adapter Tests")
class BalanceJdbcAdapterTest {

    private val jdbcTemplate = mock(NamedParameterJdbcTemplate::class.java)

    private val balanceJdbcAdapter: BalanceJdbcAdapter = BalanceJdbcAdapter(
        jdbcTemplate = jdbcTemplate
    )

    @Test
    @DisplayName("The adapter should use the jdbc template for query execution.")
    fun testUpdateBalance() {
        val user = UserMockFactory.createdWithId()
        val currency = CurrencyMockFactory.sampleCurrency()
        val amount = BigDecimal.TEN

        balanceJdbcAdapter.by(user, currency, amount)

        val queryCaptor = ArgumentCaptor.forClass(String::class.java)
        val parameterSourceCaptor = ArgumentCaptor.forClass(SqlParameterSource::class.java)

        verify(jdbcTemplate).update(queryCaptor.capture(), parameterSourceCaptor.capture())

        val capturedQuery = queryCaptor.value
        val capturedParameterSource = parameterSourceCaptor.value

        assertThat(capturedQuery)
            .contains("set balance = :balance", "where user_id = :userId", "and currency_id = :currencyId")

        assertEquals(user.id, capturedParameterSource.getValue("userId"))
        assertEquals(currency.id, capturedParameterSource.getValue("currencyId"))
        assertEquals(amount, capturedParameterSource.getValue("balance"))
    }

}
