package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.ListUserMovementsPortIn
import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.application.port.out.ListMovementsPortOut
import me.lemon.challenge.config.exception.InvalidCurrencyException
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Movement
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component

@Component
class ListUserMovementsUseCase(
    private val findUserAdapter: FindUserOutPort,
    private val listMovementsAdapter: ListMovementsPortOut,
    private val findCurrencyAdapter: FindCurrencyPortOut,
) : ListUserMovementsPortIn {

    override fun execute(command: ListUserMovementsPortIn.Command): List<Movement> {
        val movementType = MovementType.getBy(command.movementTypeCode)
        val currency = command.currencyCode.asCurrency()
        val user = findUserBy(command.userId)

        return listMovementsAdapter
            .by(
                user = user,
                type = movementType,
                currency = currency,
                limit = command.limit,
                offset = command.offset
            )
    }

    private fun findUserBy(id: Int): User = findUserAdapter
        .by(id)
        .orElseThrow { UserNotFoundException() }

    private fun String.asCurrency(): Currency = findCurrencyAdapter
        .by(this)
        .orElseThrow { InvalidCurrencyException() }

}
