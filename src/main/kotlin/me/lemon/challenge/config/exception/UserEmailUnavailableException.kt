package me.lemon.challenge.config.exception

import me.lemon.challenge.config.ErrorCatalog

class UserEmailUnavailableException(
    override val code: String = ErrorCatalog.USER_EMAIL_PREVIOUSLY_REGISTERED.name,
    override val message: String = ErrorCatalog.USER_EMAIL_PREVIOUSLY_REGISTERED.defaultMessage
) : UnprocessableEntityException(
    code = code,
    message = message
)
