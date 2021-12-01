package me.lemon.challenge.application.usecase

import me.lemon.challenge.application.port.`in`.RegisterMovementPortIn
import me.lemon.challenge.application.port.out.FindCurrencyPortOut
import me.lemon.challenge.application.port.out.FindUserOutPort
import me.lemon.challenge.application.port.out.RegisterMovementPortOut
import me.lemon.challenge.application.port.out.UpdateBalancePortOut
import me.lemon.challenge.config.exception.InvalidCurrencyException
import me.lemon.challenge.config.exception.UnprocessableMovementException
import me.lemon.challenge.config.exception.UserNotFoundException
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Movement
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.domain.User
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RegisterMovementUseCase(
    private val findUserAdapter: FindUserOutPort,
    private val findCurrencyAdapter: FindCurrencyPortOut,
    private val updateBalanceAdapter: UpdateBalancePortOut,
    private val registerMovementAdapter: RegisterMovementPortOut
) : RegisterMovementPortIn {

    override fun execute(command: RegisterMovementPortIn.Command): Movement {
        val user = findUser(command.userId)
        val currency = findCurrency(command.currencyCode)
        val type = MovementType.getBy(command.movementTypeCode)
        val currentAmount = user.getCurrentAmountInWalletFor(currency)
        val movementAmount = command.amount

        val newBalanceAmount = calculateByType(type, currentAmount, movementAmount)

        return Movement(user, currency, type, movementAmount, currentAmount)
            .also { updateBalanceAdapter.by(it.user, it.currency, newBalanceAmount) }
            .also { registerMovementAdapter.with(it) }
    }

    private fun findUser(userId: Int): User = findUserAdapter
        .by(userId)
        .orElseThrow { UserNotFoundException() }

    private fun findCurrency(currencyCode: String): Currency = findCurrencyAdapter
        .by(currencyCode)
        .orElseThrow { InvalidCurrencyException() }

    private fun calculateByType(
        type: MovementType,
        currentAmount: BigDecimal,
        movementAmount: BigDecimal
    ): BigDecimal {
        val newBalanceAmount = when (type) {
            MovementType.DEPOSIT -> currentAmount + movementAmount
            MovementType.WITHDRAW -> currentAmount - movementAmount
        }
        if (newBalanceAmount < BigDecimal.ZERO) throw UnprocessableMovementException()
        return newBalanceAmount
    }

    private fun User.getCurrentAmountInWalletFor(currency: Currency): BigDecimal = this
        .wallet
        .balances
        .first { currency == it.currency }
        .amount

}
