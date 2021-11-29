package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.mock.CurrencyMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@DisplayName("Currency Jdbc Adapter Tests")
@ExtendWith(MockitoExtension::class)
class CurrencyJdbcAdapterTest {

    private val currencyJdbcRepository: CurrencyJdbcRepository = mock(CurrencyJdbcRepository::class.java)

    private val currencyAdapter = CurrencyJdbcAdapter(
        currencyJdbcRepository = currencyJdbcRepository
    )

    @Test
    @DisplayName("When query() is executed, CurrencyJdbcRepository.findAll() should be invoked.")
    fun testQueryCurrencies() {
        val mockedJdbcCurrencies = CurrencyMockFactory.currenciesJdbc()
        val expectedCurrency = CurrencyMockFactory.sampleCurrency()

        `when`(currencyJdbcRepository.findAll()).thenReturn(mockedJdbcCurrencies)

        val result = currencyAdapter.query()

        assertThat(result).contains(expectedCurrency)

        verify(currencyJdbcRepository).findAll()
    }

}
