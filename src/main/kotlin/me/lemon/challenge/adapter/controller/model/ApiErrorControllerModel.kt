package me.lemon.challenge.adapter.controller.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ApiErrorControllerModel(
    val code: String,
    val message: String
)
