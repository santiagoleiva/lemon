package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.out.ExistsUserPortOut
import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.UserAliasUnavailableException
import me.lemon.challenge.config.exception.UserEmailUnavailableException
import me.lemon.challenge.domain.User
import me.lemon.challenge.mock.CommandMockFactory
import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import me.lemon.challenge.mock.WalletMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

@DisplayName("Create User Use Case Tests")
class CreateUserUseCaseTest {

    private val upsertUserAdapter: UpsertUserPortOut = mock(UpsertUserPortOut::class.java)
    private val findCurrencyAdapter: FindCurrencyPortOut = mock(FindCurrencyPortOut::class.java)
    private val existsUserAdapter: ExistsUserPortOut = mock(ExistsUserPortOut::class.java)

    private val useCase: CreateUserPortIn = CreateUserUseCase(
        upsertUserAdapter = upsertUserAdapter,
        findCurrencyAdapter = findCurrencyAdapter,
        existsUserAdapter = existsUserAdapter
    )

    @Test
    @DisplayName("User creation should fail if user alias is unavailable.")
    fun testCreateUserWithInvalidAliasShouldFail() {
        val command = CommandMockFactory.createUser()

        `when`(existsUserAdapter.byAlias(anyString())).thenReturn(true)
        `when`(existsUserAdapter.byEmail(anyString())).thenReturn(false)

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(UserAliasUnavailableException::class.java)
            .hasMessage(ErrorCatalog.USER_ALIAS_NOT_AVAILABLE.defaultMessage)
    }

    @Test
    @DisplayName("User creation should fail if user email is unavailable.")
    fun testCreateUserWithInvalidEmailShouldFail() {
        val command = CommandMockFactory.createUser()

        `when`(existsUserAdapter.byAlias(anyString())).thenReturn(false)
        `when`(existsUserAdapter.byEmail(anyString())).thenReturn(true)

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(UserEmailUnavailableException::class.java)
            .hasMessage(ErrorCatalog.USER_EMAIL_PREVIOUSLY_REGISTERED.defaultMessage)
    }

    @Test
    @DisplayName("Create user with with empty balances in wallet")
    fun testUserCreationSuccessfully() {
        val command = CommandMockFactory.createUser()
        val currencies = CurrencyMockFactory.sampleCurrencies()
        val createdWithId = UserMockFactory.createdWithId()
        val expectedWallet = WalletMockFactory.sampleWalletInZero()

        `when`(existsUserAdapter.byAlias(anyString())).thenReturn(false)
        `when`(existsUserAdapter.byEmail(anyString())).thenReturn(false)
        `when`(findCurrencyAdapter.all()).thenReturn(currencies)
        `when`(upsertUserAdapter.create(any(User::class.java))).thenReturn(createdWithId)

        val result = useCase.execute(command)

        assertEquals(1, result.id)
        assertEquals("user-firstname-test", result.firstname)
        assertEquals("user-lastname-test", result.lastname)
        assertEquals("alias-test", result.alias)
        assertEquals("test@lemon.com", result.email)
        assertEquals(expectedWallet, result.wallet)
    }

    companion object {

        private fun <T> any(type: Class<T>): T = Mockito.any(type)

    }

}
