package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.application.port.out.ListMovementsPortOut
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.InvalidCurrencyException
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.mock.CommandMockFactory
import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

@DisplayName("List User Movements Use Case Tests")
class ListUserMovementsUseCaseTest {

    private val findUserAdapter = mock(FindUserOutPort::class.java)
    private val findCurrencyAdapter = mock(FindCurrencyPortOut::class.java)
    private val listMovementsAdapter = mock(ListMovementsPortOut::class.java)

    private val user = UserMockFactory.createdWithId()
    private val currency = CurrencyMockFactory.sampleCurrency()

    private val useCase = ListUserMovementsUseCase(
        findUserAdapter = findUserAdapter,
        findCurrencyAdapter = findCurrencyAdapter,
        listMovementsAdapter = listMovementsAdapter
    )

    @BeforeEach
    fun init() {
        `when`(findUserAdapter.by(anyInt()))
            .thenReturn(Optional.of(user))

        `when`(findCurrencyAdapter.by(anyString()))
            .thenReturn(Optional.of(currency))
    }

    @Test
    @DisplayName("When currency by code and is not present, an InvalidCurrencyException should be thrown.")
    fun testListMovementsFailIfCurrencyIsNotValid() {
        val command = CommandMockFactory.listUserMovements()

        `when`(findCurrencyAdapter.by(anyString())).thenReturn(Optional.empty())

        val exception = catchThrowable { useCase.execute(command) }

        assertThat(exception)
            .isInstanceOf(InvalidCurrencyException::class.java)
            .hasMessage(ErrorCatalog.INVALID_CURRENCY.defaultMessage)
    }

    @Test
    @DisplayName("When user is not found, an UserNotFoundException should be thrown.")
    fun testListMovementsFailIfUserNotExists() {
        val command = CommandMockFactory.listUserMovements()

        `when`(findUserAdapter.by(anyInt())).thenReturn(Optional.empty())

        val exception = catchThrowable { useCase.execute(command) }

        assertThat(exception)
            .isInstanceOf(UserNotFoundException::class.java)
            .hasMessage(ErrorCatalog.USER_NOT_FOUND.defaultMessage)
    }

    @Test
    @DisplayName("List movements successfully.")
    fun testListMovementsSuccessfully() {
        val command = CommandMockFactory.listUserMovements()

        useCase.execute(command)

        verify(listMovementsAdapter)
            .by(
                user = user,
                type = MovementType.DEPOSIT,
                currency = currency,
                limit = 10,
                offset = 0
            )
    }

}
