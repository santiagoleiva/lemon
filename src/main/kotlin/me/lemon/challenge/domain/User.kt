package me.lemon.challenge.domain

data class User(
    val id: Int? = null,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String,
    val wallet: MutableList<Balance> = mutableListOf()
)
