package me.lemon.challenge.domain

data class CreateUserCommand(
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String
)