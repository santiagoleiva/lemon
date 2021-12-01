package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.MovementControllerModel
import me.lemon.challenge.adapter.controller.model.RegisterMovementControllerModel
import me.lemon.challenge.application.port.`in`.ListUserMovementsPortIn
import me.lemon.challenge.application.port.`in`.RegisterMovementPortIn
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users/{userId}/movements")
class UserMovementControllerAdapter(
    private val registerMovementPortIn: RegisterMovementPortIn,
    private val listUserMovementsPortIn: ListUserMovementsPortIn
) {

    @PostMapping
    fun createMovement(
        @PathVariable userId: Int,
        @RequestBody request: RegisterMovementControllerModel
    ): ResponseEntity<MovementControllerModel> = toRegisterCommand(userId, request)
        .also { logger.info("Attempt to register movement for user {} with request {}", userId, request) }
        .let { command -> registerMovementPortIn.execute(command) }
        .let { movement -> MovementControllerModel.from(movement) }
        .let { movementControllerModel -> ResponseEntity.ok(movementControllerModel) }
        .also { response -> logger.info("Movement registered successfully: {}", response) }

    @GetMapping
    fun getMovements(
        @PathVariable userId: Int,
        @RequestParam(defaultValue = "10") limit: Int,
        @RequestParam(defaultValue = "1") offset: Int,
        @RequestParam currency: String,
        @RequestParam type: String
    ): ResponseEntity<List<MovementControllerModel>> = toListCommand(userId, limit, offset, currency, type)
        .also { logger.info("Attempt to list movements with params {}", it) }
        .let { command -> listUserMovementsPortIn.execute(command) }
        .map { movement -> MovementControllerModel.from(movement) }
        .let { movements -> ResponseEntity.ok(movements) }
        .also { response -> logger.info("Returning movements: {}", response) }

    private fun toRegisterCommand(
        userId: Int,
        request: RegisterMovementControllerModel
    ): RegisterMovementPortIn.Command = RegisterMovementPortIn.Command(
        userId = userId,
        currencyCode = request.currencyCode,
        movementTypeCode = request.movementType.uppercase(),
        amount = request.amount
    )

    private fun toListCommand(
        userId: Int,
        limit: Int,
        offset: Int,
        currency: String,
        type: String
    ): ListUserMovementsPortIn.Command = ListUserMovementsPortIn.Command(
        userId = userId,
        movementTypeCode = type,
        currencyCode = currency,
        limit = limit,
        offset = offset
    )


    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(UserMovementControllerAdapter::class.java)
    }

}
