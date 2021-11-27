package me.lemon.challenge.domain

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String
)
