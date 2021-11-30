package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

open class BadRequestException(
    override val code: String = ErrorCatalog.INVALID_REQUEST.name,
    override val message: String = ErrorCatalog.INVALID_REQUEST.defaultMessage
) : GenericException(
    code = code,
    message = message
)
