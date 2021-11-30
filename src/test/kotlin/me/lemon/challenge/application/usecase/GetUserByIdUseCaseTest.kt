package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.GetUserByIdPortIn
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.mock.UserMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

@DisplayName("Get User By Id Use Case Tests")
class GetUserByIdUseCaseTest {

    private val findUserAdapter = mock(FindUserOutPort::class.java)

    private val useCase: GetUserByIdPortIn = GetUserByIdUseCase(
        findUserAdapter = findUserAdapter
    )

    @Test
    @DisplayName("If adapter returns a non-present user, an UserNotFoundException should be thrown.")
    fun testFindUserByIdFailsIfUserIsNotPresent() {
        val userId = 1
        `when`(findUserAdapter.by(anyInt())).thenReturn(Optional.empty())

        val exception = catchThrowable {
            useCase.execute(userId)
        }

        assertThat(exception)
            .isInstanceOf(UserNotFoundException::class.java)
            .hasMessage(ErrorCatalog.USER_NOT_FOUND.defaultMessage)
    }

    @Test
    @DisplayName("Get user by id successfully.")
    fun testFindUserById() {
        val userId = 2
        val mockedUser = UserMockFactory.createdWithId(userId)
        `when`(findUserAdapter.by(anyInt())).thenReturn(Optional.of(mockedUser))

        val result = useCase.execute(userId)

        assertEquals(2, result.id)
        assertEquals("user-firstname-test", result.firstname)
        assertEquals("user-lastname-test", result.lastname)
        assertEquals("alias-test", result.alias)
        assertEquals("test@lemon.com", result.email)
    }

}
