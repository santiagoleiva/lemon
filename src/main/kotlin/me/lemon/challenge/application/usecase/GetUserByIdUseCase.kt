package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.GetUserByIdPortIn
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(
    private val findUserAdapter: FindUserOutPort
) : GetUserByIdPortIn {

    override fun execute(id: Int): User = findUserAdapter
        .by(id)
        .orElseThrow { UserNotFoundException() }

}
