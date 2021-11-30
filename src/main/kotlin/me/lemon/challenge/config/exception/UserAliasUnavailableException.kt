package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class UserAliasUnavailableException(
    override val code: String = ErrorCatalog.USER_ALIAS_NOT_AVAILABLE.name,
    override val message: String = ErrorCatalog.USER_ALIAS_NOT_AVAILABLE.defaultMessage
) : UnprocessableEntityException(
    code = code,
    message = message
)
