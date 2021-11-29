package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.GetUserByIdPortIn
import me.lemon.challenge.application.port.out.QueryUserOutPort
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class GetUserByIdUseCase(
    private val queryUserAdapter: QueryUserOutPort
) : GetUserByIdPortIn {

    override fun execute(id: Int): User = queryUserAdapter.by(id)

}
