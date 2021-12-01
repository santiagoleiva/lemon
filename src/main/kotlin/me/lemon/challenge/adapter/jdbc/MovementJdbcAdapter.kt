package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import me.lemon.challenge.application.port.out.ListMovementsPortOut
import me.lemon.challenge.application.port.out.RegisterMovementPortOut
import me.lemon.challenge.domain.Movement
import org.springframework.stereotype.Component

@Component
class MovementJdbcAdapter(
    private val movementJdbcRepository: MovementJdbcRepository
) : RegisterMovementPortOut, ListMovementsPortOut {

    override fun with(movement: Movement) {
        movementJdbcRepository.save(movement.toJdbcModel())
    }

    override fun by(user: Int, type: String, currency: String, limit: Int, offset: Int): List<Movement> {
        return emptyList()
    }

    private fun Movement.toJdbcModel(): MovementJdbcModel = MovementJdbcModel(
        userId = this.user.id!!,
        currencyId = this.currency.id,
        type = this.type.name,
        amount = this.amount,
        previousBalance = this.previousBalance
    )

}
