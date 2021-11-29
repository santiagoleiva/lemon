package me.lemon.challenge.application.port.out

interface ExistsUserPortOut {

    fun byAlias(alias: String): Boolean

    fun byEmail(email: String): Boolean

}
