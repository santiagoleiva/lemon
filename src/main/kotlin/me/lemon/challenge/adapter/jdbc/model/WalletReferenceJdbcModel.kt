package me.lemon.challenge.adapter.jdbc.model

import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("user_wallet")
data class WalletReferenceJdbcModel(
    val currencyId: Int,
    val balance: BigDecimal
)
