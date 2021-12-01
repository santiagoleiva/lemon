package me.lemon.challenge.adapter.jdbc.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("movement")
data class MovementJdbcModel(
    @Id val id: Long? = null,
    val userId: Int,
    val currencyId: Int,
    val type: String,
    val amount: BigDecimal,
    val previousBalance: BigDecimal,
    val createdAt: LocalDateTime
)
