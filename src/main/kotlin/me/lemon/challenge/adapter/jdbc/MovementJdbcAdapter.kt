package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import me.lemon.challenge.application.port.out.ListMovementsPortOut
import me.lemon.challenge.application.port.out.RegisterMovementPortOut
import me.lemon.challenge.domain.Currency
import me.lemon.challenge.domain.Movement
import me.lemon.challenge.domain.MovementType
import me.lemon.challenge.domain.User
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class MovementJdbcAdapter(
    private val movementJdbcRepository: MovementJdbcRepository
) : RegisterMovementPortOut, ListMovementsPortOut {

    override fun with(movement: Movement) {
        movementJdbcRepository.save(movement.toJdbcModel())
    }

    override fun by(user: User, type: MovementType, currency: Currency, limit: Int, offset: Int): List<Movement> {
        val page = offset - 1
        val pageRequest = PageRequest.of(page, limit)
        return movementJdbcRepository
            .findByUserIdAndTypeAndCurrencyIdOrderByCreatedAtDesc(
                userId = user.id!!,
                type = type.name,
                currencyId = currency.id,
                pageable = pageRequest
            )
            .map { jdbcModel ->
                Movement(
                    id = jdbcModel.id,
                    user = user,
                    currency = currency,
                    type = type,
                    amount = jdbcModel.amount,
                    previousBalance = jdbcModel.previousBalance,
                    createdAt = jdbcModel.createdAt
                )
            }
    }

    private fun Movement.toJdbcModel(): MovementJdbcModel = MovementJdbcModel(
        userId = this.user.id!!,
        currencyId = this.currency.id,
        type = this.type.name,
        amount = this.amount,
        previousBalance = this.previousBalance,
        createdAt = this.createdAt
    )

}
