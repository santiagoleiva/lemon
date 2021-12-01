package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.ListUserMovementsPortIn
import me.lemon.challenge.domain.Movement
import org.springframework.stereotype.Component

@Component
class ListUserMovementsUseCase : ListUserMovementsPortIn {

    override fun execute(command: ListUserMovementsPortIn.Command): List<Movement> {
        TODO("Not yet implemented")
    }

}
