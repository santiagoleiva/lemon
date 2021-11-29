package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.ApiErrorControllerModel
import me.lemon.challenge.config.ErrorCatalog
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleThrowable(ex: Throwable): ResponseEntity<ApiErrorControllerModel> {
        logger.error("Handle exception: {}", ex.localizedMessage, ex)
        val error = ApiErrorControllerModel(
            code = ErrorCatalog.INTERNAL_ERROR.name,
            message = ErrorCatalog.INTERNAL_ERROR.defaultMessage
        )
        return ResponseEntity.internalServerError().body(error)
    }

    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    }

}
