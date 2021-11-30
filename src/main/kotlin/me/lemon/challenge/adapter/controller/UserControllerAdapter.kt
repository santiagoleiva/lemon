package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.CreateUserControllerModel
import me.lemon.challenge.adapter.controller.model.UserControllerModel
import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.application.port.`in`.GetUserByIdPortIn
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserControllerAdapter(
    private val createUserPortIn: CreateUserPortIn,
    private val getUserByIdPortIn: GetUserByIdPortIn
) {

    @PostMapping
    fun createUser(
        @RequestBody request: CreateUserControllerModel
    ): ResponseEntity<UserControllerModel> = request
        .also { logger.info("Attempt to create user with request {}", it) }
        .let { createUserPortIn.execute(it.toCommand()) }
        .let { user -> UserControllerModel.from(user) }
        .let { controllerModel -> ResponseEntity(controllerModel, HttpStatus.CREATED) }
        .also { response -> logger.info("User created successfully: {}", response) }

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Int
    ): ResponseEntity<UserControllerModel> = id
        .also { logger.info("Attempt to get user with id {}", it) }
        .let { getUserByIdPortIn.execute(it) }
        .let { user -> UserControllerModel.from(user) }
        .let { userControllerModel -> ResponseEntity.ok(userControllerModel) }
        .also { response -> logger.info("User obtained successfully: {}", response) }

    private fun CreateUserControllerModel.toCommand(): CreateUserPortIn.Command = CreateUserPortIn.Command(
        firstname = this.firstname,
        lastname = this.lastname,
        alias = this.alias,
        email = this.email
    )

    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(UserControllerAdapter::class.java)
    }

}
