package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

open class ResourceNotFoundException(
    override val code: String = ErrorCatalog.RESOURCE_NOT_FOUND.name,
    override val message: String = ErrorCatalog.RESOURCE_NOT_FOUND.defaultMessage
) : GenericException(
    code = code,
    message = message
)