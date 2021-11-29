package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

open class UnprocessableEntityException(
    override val code: String = ErrorCatalog.UNPROCESSABLE_ENTITY.name,
    override val message: String = ErrorCatalog.UNPROCESSABLE_ENTITY.defaultMessage
) : GenericException(
    code = code,
    message = message
)