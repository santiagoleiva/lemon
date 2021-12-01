package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.application.port.out.RegisterMovementPortOut
import me.lemon.challenge.application.port.out.UpdateBalancePortOut
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.InvalidCurrencyException
import me.lemon.challenge.config.exception.InvalidMovementTypeException
import me.lemon.challenge.config.exception.UnprocessableMovementException
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.mock.CommandMockFactory
import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.math.BigDecimal
import java.util.*

@DisplayName("Register Movement Use Case Tests")
class RegisterMovementUseCaseTest {

    private val findUserAdapter = mock(FindUserOutPort::class.java)
    private val findCurrencyAdapter = mock(FindCurrencyPortOut::class.java)
    private val updateBalanceAdapter = mock(UpdateBalancePortOut::class.java)
    private val registerMovementAdapter = mock(RegisterMovementPortOut::class.java)

    private val user = UserMockFactory.createdWithId()
    private val currency = CurrencyMockFactory.sampleCurrency()

    private val useCase = RegisterMovementUseCase(
        findUserAdapter = findUserAdapter,
        findCurrencyAdapter = findCurrencyAdapter,
        updateBalanceAdapter = updateBalanceAdapter,
        registerMovementAdapter = registerMovementAdapter
    )

    @BeforeEach
    fun init() {
        `when`(findUserAdapter.by(anyInt()))
            .thenReturn(Optional.of(user))

        `when`(findCurrencyAdapter.by(anyString()))
            .thenReturn(Optional.of(currency))
    }

    @Test
    @DisplayName("When user is not found, an UserNotFoundException should be thrown.")
    fun testRegisterMovementFailsIfUserIsNotFound() {
        val command = CommandMockFactory.registerMovement()

        `when`(findUserAdapter.by(anyInt())).thenReturn(Optional.empty())

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(UserNotFoundException::class.java)
            .hasMessage(ErrorCatalog.USER_NOT_FOUND.defaultMessage)
    }

    @Test
    @DisplayName("When currency by code and is not present, an InvalidCurrencyException should be thrown.")
    fun testRegisterMovementFailsIfCurrencyIsInvalid() {
        val command = CommandMockFactory.registerMovement()

        `when`(findCurrencyAdapter.by(anyString())).thenReturn(Optional.empty())

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(InvalidCurrencyException::class.java)
            .hasMessage(ErrorCatalog.INVALID_CURRENCY.defaultMessage)
    }

    @Test
    @DisplayName("When movement type is invalid, an InvalidMovementTypeException should be thrown.")
    fun testRegisterMovementFailsIfMovementTypeIsInvalid() {
        val command = CommandMockFactory.registerMovementWithInvalidType()

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(InvalidMovementTypeException::class.java)
            .hasMessage(ErrorCatalog.INVALID_MOVEMENT_TYPE.defaultMessage)
    }

    @Test
    @DisplayName("If new balance is negative, an UnprocessableMovementException should be thrown.")
    fun testRegisterMovementFailsIfNewBalanceIsNegative() {
        val command = CommandMockFactory.registerMovementAmountGreaterThanBalance()

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(UnprocessableMovementException::class.java)
            .hasMessage(ErrorCatalog.UNPROCESSABLE_MOVEMENT.defaultMessage)
    }

    @Test
    @DisplayName("When movement is registered the balance should be updated.")
    fun testRegisterMovementSuccessfully() {
        val command = CommandMockFactory.registerMovement()

        val result = useCase.execute(command)

        assertEquals(user, result.user)
        assertEquals(currency, result.currency)
        assertEquals(MovementType.DEPOSIT, result.type)
        assertEquals(BigDecimal.TEN, result.amount)
        assertEquals(BigDecimal.ZERO, result.previousBalance)
    }

}
