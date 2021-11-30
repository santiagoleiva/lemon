package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class InvalidCurrencyException(
    code: String = ErrorCatalog.INVALID_CURRENCY.name,
    message: String = ErrorCatalog.INVALID_CURRENCY.defaultMessage
) : BadRequestException(
    code = code,
    message = message
)
