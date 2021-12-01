package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.ListUserMovementsPortIn
import me.lemon.challenge.application.port.out.ListMovementsPortOut
import me.lemon.challenge.domain.Movement
import org.springframework.stereotype.Component

@Component
class ListUserMovementsUseCase(
    private val listMovementsAdapter: ListMovementsPortOut
) : ListUserMovementsPortIn {

    override fun execute(command: ListUserMovementsPortIn.Command): List<Movement> = listMovementsAdapter
        .by(
            user = command.userId,
            type = command.movementType,
            currency = command.currencyCode,
            limit = command.limit,
            offset = command.offset
        )

}
