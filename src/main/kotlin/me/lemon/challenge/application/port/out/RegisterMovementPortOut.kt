package me.lemon.challenge.application.port.out

import me.lemon.challenge.domain.Movement

interface RegisterMovementPortOut {

    fun with(movement: Movement)

}
