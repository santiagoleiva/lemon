package me.lemon.challenge.adapter.jdbc.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("currency")
data class CurrencyJdbcModel(
    @Id val id: Int,
    val code: String,
    val description: String,
    val decimals: Int
)
