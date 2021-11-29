package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class UserNotFoundException(
    override val code: String = ErrorCatalog.USER_NOT_FOUND.name,
    override val message: String = ErrorCatalog.USER_NOT_FOUND.defaultMessage
) : ResourceNotFoundException(
    code = code,
    message = message
)
