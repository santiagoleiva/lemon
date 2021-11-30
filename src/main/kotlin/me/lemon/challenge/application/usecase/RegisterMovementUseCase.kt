package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.RegisterMovementPortIn
import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.config.exception.CurrencyNotFoundException
import me.lemon.challenge.config.exception.InvalidMovementTypeException
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Movement
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.domain.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RegisterMovementUseCase(
    private val findUserAdapter: FindUserOutPort,
    private val findCurrencyAdapter: FindCurrencyPortOut
) : RegisterMovementPortIn {

    override fun execute(command: RegisterMovementPortIn.Command): Movement {
        val user = findUser(command.userId)
        val currency = findCurrency(command.currencyCode)
        val type = typeBy(command.movementType)
        return Movement(
            user = user,
            currency = currency,
            type = type,
            amount = command.amount,
            previousBalance = BigDecimal.ZERO
        )
    }

    private fun findUser(userId: Int): User = findUserAdapter
        .by(userId)
        .orElseThrow { UserNotFoundException() }

    private fun findCurrency(currencyCode: String): Currency = findCurrencyAdapter
        .by(currencyCode)
        .orElseThrow { CurrencyNotFoundException() }

    private fun typeBy(code: String) = try {
        MovementType.valueOf(code)
    } catch (exception: IllegalArgumentException) {
        logger.error("Invalid movement type {}", code, exception)
        throw InvalidMovementTypeException()
    }

    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(RegisterMovementUseCase::class.java)
    }

}
