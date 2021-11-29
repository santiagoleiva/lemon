package me.lemon.challenge.adapter.jdbc

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
    @DisplayName("Return `true` when UserJdbcRepository.existsByAlias() returns true")
    fun testExistsUserByAlias() {
        val alias = "alias-test"

        `when`(userJdbcRepository.existsByAlias(anyString())).thenReturn(true)

        assertTrue(userJdbcAdapter.byAlias(alias))

        verify(userJdbcRepository).existsByAlias(alias)
    }

    @Test
    @DisplayName("Return `true` when UserJdbcRepository.existsByEmail() returns true")
    fun testExistsUserByEmail() {
        val alias = "alias-test"

        `when`(userJdbcRepository.existsByEmail(anyString())).thenReturn(true)

        assertTrue(userJdbcAdapter.byEmail(alias))

        verify(userJdbcRepository).existsByEmail(alias)
    }

}
