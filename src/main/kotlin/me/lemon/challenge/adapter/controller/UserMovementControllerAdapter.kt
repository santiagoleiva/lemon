package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.MovementControllerModel
import me.lemon.challenge.adapter.controller.model.RegisterMovementControllerModel
import me.lemon.challenge.application.port.`in`.RegisterMovementPortIn
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users/{userId}/movements")
class UserMovementControllerAdapter(
    private val registerMovementPortIn: RegisterMovementPortIn
) {

    @PostMapping
    fun createMovement(
        @PathVariable userId: Int,
        @RequestBody request: RegisterMovementControllerModel
    ): ResponseEntity<MovementControllerModel> = toCommand(userId, request)
        .also { logger.info("Attempt to register movement for user {} with request {}", userId, request) }
        .let { command -> registerMovementPortIn.execute(command) }
        .let { movement -> MovementControllerModel.from(movement) }
        .let { movementControllerModel -> ResponseEntity.ok().body(movementControllerModel) }
        .also { response -> logger.info("Movement registered successfully: {}", response) }

    private fun toCommand(
        userId: Int,
        request: RegisterMovementControllerModel
    ): RegisterMovementPortIn.Command = RegisterMovementPortIn.Command(
        userId = userId,
        currencyCode = request.currencyCode,
        movementType = request.movementType.uppercase(),
        amount = request.amount
    )


    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(UserMovementControllerAdapter::class.java)
    }

}
