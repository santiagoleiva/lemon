package me.lemon.challenge.config.exception

open class GenericException(
    open val code: String,
    override val message: String
) : RuntimeException(message)