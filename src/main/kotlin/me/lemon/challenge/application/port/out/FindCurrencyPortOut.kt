package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Currency

interface FindCurrencyPortOut {

    fun all(): List<Currency>

}
