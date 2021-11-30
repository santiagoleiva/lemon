package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.User
import java.math.BigDecimal

interface UpdateBalancePortOut {

    fun by(user: User, currency: Currency, amount: BigDecimal)

}
