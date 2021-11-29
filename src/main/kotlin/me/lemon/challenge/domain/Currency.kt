package me.lemon.challenge.domain

data class Currency(
    val id: Int,
    val code: String,
    val description: String,
    val decimals: Int
)
