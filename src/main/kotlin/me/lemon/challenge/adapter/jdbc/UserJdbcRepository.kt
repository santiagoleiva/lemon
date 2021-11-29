package me.lemon.challenge.adapter.jdbc

import me.lemon.challenge.adapter.jdbc.model.UserJdbcModel
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJdbcRepository : PagingAndSortingRepository<UserJdbcModel, Int>
