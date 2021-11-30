package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class CurrencyNotFoundException(
    code: String = ErrorCatalog.CURRENCY_NOT_FOUND.name,
    message: String = ErrorCatalog.CURRENCY_NOT_FOUND.defaultMessage
) : ResourceNotFoundException(
    code = code,
    message = message
)
