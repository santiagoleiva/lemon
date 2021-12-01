package me.lemon.challenge.mock

import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.`in`.ListUserMovementsPortIn
import me.lemon.challenge.application.port.`in`.RegisterMovementPortIn
import me.lemon.challenge.domain.MovementType
import java.math.BigDecimal

object CommandMockFactory {

    fun createUser(): CreateUserPortIn.Command = CreateUserPortIn.Command(
        firstname = "firstname-test",
        lastname = "lastname-test",
        alias = "alias-test",
        email = "test@lemon.me"
    )

    fun registerMovement(): RegisterMovementPortIn.Command = RegisterMovementPortIn.Command(
        userId = UserMockFactory.defaultUserId,
        currencyCode = CurrencyMockFactory.defaultCurrencyCode,
        movementTypeCode = WalletMockFactory.defaultMovementType.name,
        amount = WalletMockFactory.defaultAmount,
    )

    fun registerMovementWithInvalidType(): RegisterMovementPortIn.Command = registerMovement()
        .copy(movementTypeCode = "INVALID_MOVEMENT_TYPE")

    fun registerMovementAmountGreaterThanBalance(): RegisterMovementPortIn.Command = registerMovement()
        .copy(
            movementTypeCode = MovementType.WITHDRAW.name,
            amount = BigDecimal(Int.MAX_VALUE)
        )

    fun listUserMovements(): ListUserMovementsPortIn.Command = ListUserMovementsPortIn.Command(
        userId = UserMockFactory.defaultUserId,
        movementTypeCode = WalletMockFactory.defaultMovementType.name,
        currencyCode = CurrencyMockFactory.defaultCurrencyCode,
        limit = 10,
        offset = 0,
    )

}
