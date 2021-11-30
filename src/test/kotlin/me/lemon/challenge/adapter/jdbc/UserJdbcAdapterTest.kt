package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.mock.CurrencyMockFactory
import me.lemon.challenge.mock.UserMockFactory
import me.lemon.challenge.mock.WalletMockFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

@DisplayName("User Jdbc Adapter Tests")
class UserJdbcAdapterTest {

    private val userJdbcRepository = mock(UserJdbcRepository::class.java)
    private val currencyJdbcRepository = mock(CurrencyJdbcRepository::class.java)

    private val userJdbcAdapter = UserJdbcAdapter(
        userJdbcRepository = userJdbcRepository,
        currencyJdbcRepository = currencyJdbcRepository
    )

    @Test
    @DisplayName("Test create user. Should invoke UserJdbcRepository.save() and returns the saved entity.")
    fun testCreateUser() {
        val userToCreate = UserMockFactory.toCreate()
        val expectedCreatedUser = UserMockFactory.createdWithId()
        val mockedUserJdbc = UserMockFactory.sampleUserJdbc()
        val expectedModelToSave = mockedUserJdbc.copy(id = null)

        `when`(userJdbcRepository.save(any())).thenReturn(mockedUserJdbc)

        val result = userJdbcAdapter.create(userToCreate)

        assertEquals(result, expectedCreatedUser)

        verify(userJdbcRepository).save(expectedModelToSave)
    }

    @Test
    @DisplayName("Return `true` when UserJdbcRepository.existsByAlias() returns true.")
    fun testExistsUserByAlias() {
        val alias = "alias-test"

        `when`(userJdbcRepository.existsByAlias(anyString())).thenReturn(true)

        assertTrue(userJdbcAdapter.byAlias(alias))

        verify(userJdbcRepository).existsByAlias(alias)
    }

    @Test
    @DisplayName("Return `true` when UserJdbcRepository.existsByEmail() returns true.")
    fun testExistsUserByEmail() {
        val alias = "alias-test"

        `when`(userJdbcRepository.existsByEmail(anyString())).thenReturn(true)

        assertTrue(userJdbcAdapter.byEmail(alias))

        verify(userJdbcRepository).existsByEmail(alias)
    }

    @Test
    @DisplayName("When user is not present, an UserNotFoundException should be thrown.")
    fun testFindUserByIdError() {
        val userId = 1

        `when`(userJdbcRepository.findById(anyInt())).thenReturn(Optional.empty())

        val exception = catchThrowable {
            userJdbcAdapter.by(userId)
        }

        assertThat(exception)
            .isInstanceOf(UserNotFoundException::class.java)
            .hasMessage(ErrorCatalog.USER_NOT_FOUND.defaultMessage)
    }

    @Test
    @DisplayName("Find user by id should return the persisted entity as domain.")
    fun testFindUserById() {
        val userId = 10
        val jdbcEntity = UserMockFactory.sampleUserJdbc()
        val jdbcCurrencies = CurrencyMockFactory.currenciesJdbc()
        val expectedUserWallet = WalletMockFactory.sampleWallet()

        `when`(userJdbcRepository.findById(anyInt())).thenReturn(Optional.of(jdbcEntity))
        `when`(currencyJdbcRepository.findAllById(anyCollection())).thenReturn(jdbcCurrencies)

        val result = userJdbcAdapter.by(userId)

        assertEquals(1, result.id)
        assertEquals("user-firstname-test", result.firstname)
        assertEquals("user-lastname-test", result.lastname)
        assertEquals("alias-test", result.alias)
        assertEquals("test@lemon.com", result.email)
        assertEquals(expectedUserWallet, result.wallet)
    }

}
