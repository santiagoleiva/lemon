package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.CreateUserControllerModel
import me.lemon.challenge.adapter.controller.model.UserControllerModel
import me.lemon.challenge.application.port.`in`.CreateUserPortIn
import me.lemon.challenge.domain.CreateUserCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
class UserControllerAdapter(
    private val createUserPortIn: CreateUserPortIn
){

    @PostMapping
    fun createUser(
        @RequestBody request: CreateUserControllerModel
    ): ResponseEntity<UserControllerModel> = request
        .also { logger.info("Attemp to create user with request {}", it) }
        .let { createUserPortIn.execute(it.toDomain()) }
        .let { user -> UserControllerModel.from(user) }
        .let { userControllerModel -> ResponseEntity.ok(userControllerModel) }
        .also { response -> logger.info("User created successfully: {}", response) }

    private fun CreateUserControllerModel.toDomain(): CreateUserCommand = CreateUserCommand(
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
