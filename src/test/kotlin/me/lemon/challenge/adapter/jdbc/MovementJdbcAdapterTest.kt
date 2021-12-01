package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import me.lemon.challenge.mock.WalletMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

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

}
