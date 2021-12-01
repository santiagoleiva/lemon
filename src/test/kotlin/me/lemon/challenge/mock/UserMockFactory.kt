package me.lemon.challenge.mock

import me.lemon.challenge.adapter.jdbc.model.UserJdbcModel
import me.lemon.challenge.domain.User

object UserMockFactory {

    @JvmStatic
    val defaultUserId: Int = 1

    @JvmStatic
    private val defaultFirstname: String = "user-firstname-test"

    @JvmStatic
    private val defaultLastname: String = "user-lastname-test"

    @JvmStatic
    private val defaultAlias: String = "alias-test"

    @JvmStatic
    private val defaultEmail: String = "test@lemon.com"

    fun toCreate(): User = User(
        firstname = defaultFirstname,
        lastname = defaultLastname,
        alias = defaultAlias,
        email = defaultEmail,
        wallet = WalletMockFactory.sampleWalletInZero()
    )

    fun createdWithId(id: Int = defaultUserId): User = toCreate()
        .copy(id = id)

    fun sampleUserJdbc(id: Int = defaultUserId): UserJdbcModel = UserJdbcModel(
        id = id,
        firstname = defaultFirstname,
        lastname = defaultLastname,
        alias = defaultAlias,
        email = defaultEmail,
        wallet = setOf(WalletMockFactory.sampleWalletReferenceJdbc())
    )

    fun sampleUserJdbcToCreate(): UserJdbcModel = sampleUserJdbc()
        .copy(
            id = null,
            wallet = setOf(WalletMockFactory.sampleWalletReferenceJdbcInZero())
        )

}