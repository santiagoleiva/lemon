package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.GetUserByIdPortIn
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase : GetUserByIdPortIn {

    override fun execute(id: Int): User = TODO("Not yet implemented")

}
