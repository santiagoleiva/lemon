package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.MovementJdbcModel
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MovementJdbcRepository : PagingAndSortingRepository<MovementJdbcModel, Long> {

    fun findByUserIdAndTypeAndCurrencyIdOrderByCreatedAtDesc(
        userId: Int,
        type: String,
        currencyId: Int,
        pageable: Pageable
    ): List<MovementJdbcModel>

}
