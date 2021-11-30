package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class UnprocessableMovementException(
    code: String = ErrorCatalog.UNPROCESSABLE_MOVEMENT.name,
    message: String = ErrorCatalog.UNPROCESSABLE_MOVEMENT.defaultMessage
) : UnprocessableEntityException(
    code = code,
    message = message
)
