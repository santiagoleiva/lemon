package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.mock.CurrencyMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@DisplayName("Currency Jdbc Adapter Tests")
@ExtendWith(MockitoExtension::class)
class CurrencyJdbcAdapterTest {

    private val currencyJdbcRepository: CurrencyJdbcRepository = mock(CurrencyJdbcRepository::class.java)

    private val currencyAdapter = CurrencyJdbcAdapter(
        currencyJdbcRepository = currencyJdbcRepository
    )

    @Test
    @DisplayName("When all() is executed, CurrencyJdbcRepository.findAll() should be invoked.")
    fun testQueryCurrencies() {
        val mockedJdbcCurrencies = CurrencyMockFactory.currenciesJdbc()
        val expectedCurrency = CurrencyMockFactory.sampleCurrency()

        `when`(currencyJdbcRepository.findAll()).thenReturn(mockedJdbcCurrencies)

        val result = currencyAdapter.all()

        assertThat(result).contains(expectedCurrency)

        verify(currencyJdbcRepository).findAll()
    }

    @Test
    @DisplayName("When currency is not present, the adapter should return an empty Optional.")
    fun testFindByCodeWhenCurrencyIsNotPresent() {
        val code = "code-test"

        `when`(currencyJdbcRepository.findByCode(anyString())).thenReturn(Optional.empty())

        val result = currencyAdapter.by(code)

        assertThat(result).isEmpty

        verify(currencyJdbcRepository).findByCode(code)
    }

    @Test
    @DisplayName("The adapter should return the currency as domain.")
    fun testFindByCodeOk() {
        val code = "code-test-ok"
        val currencyJdbc = CurrencyMockFactory.sampleCurrencyJdbc()

        `when`(currencyJdbcRepository.findByCode(anyString())).thenReturn(Optional.of(currencyJdbc))

        val result = currencyAdapter.by(code)

        assertThat(result)
            .isNotEmpty
            .hasValueSatisfying {
                assertEquals(1, it.id)
                assertEquals("currency-code-test", it.code)
                assertEquals("currency-description-test", it.description)
                assertEquals(2, it.decimals)
            }

        verify(currencyJdbcRepository).findByCode(code)
    }

}
