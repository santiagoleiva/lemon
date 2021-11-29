package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.CreateUserControllerModel
import me.lemon.challenge.adapter.controller.model.UserControllerModel
import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
class UserControllerAdapter(
    private val createUserPortIn: CreateUserPortIn
) {

    @PostMapping
    fun createUser(
        @RequestBody request: CreateUserControllerModel
    ): ResponseEntity<UserControllerModel> = request
        .also { logger.info("Attempt to create user with request {}", it) }
        .let { createUserPortIn.execute(it.toDomain()) }
        .let { user -> UserControllerModel.from(user) }
        .let { controllerModel -> ResponseEntity(controllerModel, HttpStatus.CREATED) }
        .also { response -> logger.info("User created successfully: {}", response) }

    private fun CreateUserControllerModel.toDomain(): CreateUserPortIn.Command = CreateUserPortIn.Command(
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
