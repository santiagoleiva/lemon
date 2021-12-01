package me.lemon.challenge.domain

import me.lemon.challenge.config.exception.InvalidMovementTypeException

enum class MovementType {

    DEPOSIT,
    WITHDRAW;

    companion object {
        fun getBy(code: String) = try {
            valueOf(code.uppercase())
        } catch (exception: IllegalArgumentException) {
            throw InvalidMovementTypeException()
        }
    }

}
