package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.mock.UserMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

@DisplayName("User Jdbc Adapter Tests")
class UserJdbcAdapterTest {

    private val userJdbcRepository = mock(UserJdbcRepository::class.java)

    private val userJdbcAdapter = UserJdbcAdapter(
        userJdbcRepository = userJdbcRepository
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

}
