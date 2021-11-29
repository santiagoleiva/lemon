package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.out.ExistsUserPortOut
import me.lemon.challenge.application.port.out.QueryCurrencyPortOut
import me.lemon.challenge.application.port.out.UpsertUserPortOut
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.UserAliasUnavailableException
import me.lemon.challenge.config.exception.UserEmailUnavailableException
import me.lemon.challenge.mock.CommandMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

@DisplayName("Create User Use Case Tests")
class CreateUserUseCaseTest {

    private val upsertUserAdapter: UpsertUserPortOut = mock(UpsertUserPortOut::class.java)
    private val queryCurrencyAdapter: QueryCurrencyPortOut = mock(QueryCurrencyPortOut::class.java)
    private val existsUserAdapter: ExistsUserPortOut = mock(ExistsUserPortOut::class.java)

    private val useCase: CreateUserPortIn = CreateUserUseCase(
        upsertUserAdapter = upsertUserAdapter,
        queryCurrencyAdapter = queryCurrencyAdapter,
        existsUserAdapter = existsUserAdapter
    )

    @Test
    @DisplayName("User creation should fail if user alias is unavailable.")
    fun testCreateUserWithInvalidAliasShouldFail() {
        val command = CommandMockFactory.forUserCreation()

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
        val command = CommandMockFactory.forUserCreation()

        `when`(existsUserAdapter.byAlias(anyString())).thenReturn(false)
        `when`(existsUserAdapter.byEmail(anyString())).thenReturn(true)

        val exception = catchThrowable {
            useCase.execute(command)
        }

        assertThat(exception)
            .isInstanceOf(UserEmailUnavailableException::class.java)
            .hasMessage(ErrorCatalog.USER_EMAIL_PREVIOUSLY_REGISTERED.defaultMessage)
    }

}
