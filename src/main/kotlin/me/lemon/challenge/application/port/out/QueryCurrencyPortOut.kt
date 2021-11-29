package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency

interface QueryCurrencyPortOut {

    fun all(): List<Currency>

}
