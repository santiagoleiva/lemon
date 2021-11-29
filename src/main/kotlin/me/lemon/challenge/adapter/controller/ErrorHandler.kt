package me.lemon.challenge.adapter.controller

import me.lemon.challenge.adapter.controller.model.ApiErrorControllerModel
import me.lemon.challenge.config.ErrorCatalog
import me.lemon.challenge.config.exception.GenericException
import me.lemon.challenge.config.exception.UnprocessableEntityException
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
    fun handleThrowable(throwable: Throwable): ResponseEntity<ApiErrorControllerModel> {
        logger.error("Handling exception: {}", throwable.localizedMessage, throwable)
        val error = ApiErrorControllerModel(
            code = ErrorCatalog.INTERNAL_ERROR.name,
            message = ErrorCatalog.INTERNAL_ERROR.defaultMessage
        )
        return ResponseEntity.internalServerError().body(error)
    }

    @ExceptionHandler(GenericException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(exception: GenericException): ResponseEntity<ApiErrorControllerModel> {
        logger.error("Handling generic exception: {}", exception.message, exception)
        val error = ApiErrorControllerModel(
            code = exception.code,
            message = exception.message
        )
        return ResponseEntity.internalServerError().body(error)
    }

    @ExceptionHandler(UnprocessableEntityException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleUnprocessableEntity(exception: UnprocessableEntityException): ResponseEntity<ApiErrorControllerModel> {
        logger.error("Handling unprocessable entity: {}", exception.message, exception)
        val error = ApiErrorControllerModel(
            code = exception.code,
            message = exception.message
        )
        return ResponseEntity.unprocessableEntity().body(error)
    }

    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    }

}
