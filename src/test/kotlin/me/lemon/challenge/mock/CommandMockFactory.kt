package me.lemon.challenge.mock

import me.lemon.challenge.application.port.`in`.CreateUserPortIn

object CommandMockFactory {

    fun forUserCreation(): CreateUserPortIn.Command = CreateUserPortIn.Command(
        firstname = "firstname-test",
        lastname = "lastname-test",
        alias = "alias-test",
        email = "test@lemon.me"
    )

}
