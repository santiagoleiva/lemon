package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import me.lemon.challenge.mock.WalletMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.data.domain.Pageable
import java.math.BigDecimal

@DisplayName("Movement Jdbc Adapter Tests")
class MovementJdbcAdapterTest {

    private val movementJdbcRepository = mock(MovementJdbcRepository::class.java)

    private val movementAdapter: MovementJdbcAdapter = MovementJdbcAdapter(
        movementJdbcRepository = movementJdbcRepository
    )

    @Test
    @DisplayName("When adapter save a movement MovementJdbcRepository.save() should be called with domain as JDBC model.")
    fun testRegisterMovement() {
        val movement = WalletMockFactory.sampleMovement()
        val argumentCaptor = ArgumentCaptor.forClass(MovementJdbcModel::class.java)

        movementAdapter.with(movement)

        verify(movementJdbcRepository).save(argumentCaptor.capture())

        val capturedJdbcArgument = argumentCaptor.value

        assertEquals(movement.user.id, capturedJdbcArgument.userId)
        assertEquals(movement.currency.id, capturedJdbcArgument.currencyId)
        assertEquals(movement.type.name, capturedJdbcArgument.type)
        assertEquals(movement.amount, capturedJdbcArgument.amount)
        assertEquals(movement.previousBalance, capturedJdbcArgument.previousBalance)
    }

    @Test
    @DisplayName("The adapter should return the given movements from JdbcRepository as domain model.")
    fun testListUserMovements() {
        val limit = 5
        val offset = 0
        val mockedJdbcMovements = listOf(WalletMockFactory.sampleMovementJdbc())

        `when`(
            movementJdbcRepository.findByUserIdAndTypeAndCurrencyIdOrderByCreatedAtDesc(
                anyInt(), anyString(), anyInt(), any(Pageable::class.java)
            )
        ).thenReturn(mockedJdbcMovements)

        val result = movementAdapter.by(
            user = UserMockFactory.createdWithId(),
            type = WalletMockFactory.defaultMovementType,
            currency = CurrencyMockFactory.sampleCurrency(),
            limit = limit,
            offset = offset
        )

        assertThat(
            result.find {
                (it.id == 1L) and (it.amount == BigDecimal.TEN) and (it.previousBalance == BigDecimal.ZERO)
            }
        ).isNotNull
    }

    companion object {
        private fun <T> any(type: Class<T>): T = Mockito.any(type)
    }

}
