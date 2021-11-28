package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyJdbcRepository : CrudRepository<CurrencyJdbcModel, Int>
