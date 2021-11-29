package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency

interface QueryCurrencyPortOut {

    fun query(): List<Currency>

}
