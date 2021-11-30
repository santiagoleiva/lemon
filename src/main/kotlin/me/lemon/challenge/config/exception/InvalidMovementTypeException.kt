package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class InvalidMovementTypeException(
    code: String = ErrorCatalog.INVALID_MOVEMENT_TYPE.name,
    message: String = ErrorCatalog.INVALID_MOVEMENT_TYPE.defaultMessage
) : UnprocessableEntityException(
    code = code,
    message = message
)
