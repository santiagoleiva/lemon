package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.CurrencyJdbcModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CurrencyJdbcRepository : CrudRepository<CurrencyJdbcModel, Int> {

    fun findByCode(code: String): Optional<CurrencyJdbcModel>

}
