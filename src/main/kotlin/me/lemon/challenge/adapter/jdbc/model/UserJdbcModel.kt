package me.lemon.challenge.adapter.jdbc.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class UserJdbcModel(
    @Id val id: Int? = null,
    val firstname: String,
    val lastname: String,
    val alias: String,
    val email: String
)
