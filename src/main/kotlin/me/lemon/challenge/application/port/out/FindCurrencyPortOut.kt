package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency
import java.util.*

interface FindCurrencyPortOut {

    fun all(): List<Currency>

    fun by(code: String): Optional<Currency>

}
