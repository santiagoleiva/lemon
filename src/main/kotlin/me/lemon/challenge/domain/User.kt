package me.lemon.challenge.domain

import java.util.Collections.emptyList

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String,
    val wallet: List<Balance> = emptyList()
)
