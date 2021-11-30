package me.lemon.challenge.config

enum class ErrorCatalog(
    val defaultMessage: String
) {

    INTERNAL_ERROR("Ha ocurrido un error inesperado"),
    INVALID_CURRENCY("La moneda es inválida"),
    INVALID_MOVEMENT_TYPE("El tipo de movimiento es inválido"),
    INVALID_REQUEST("Peticion inválida"),
    RESOURCE_NOT_FOUND("No se ha encontrado el recurso solicitado"),
    UNPROCESSABLE_ENTITY("No es posible procesar la entidad"),
    UNPROCESSABLE_MOVEMENT("No es posible procesar el movimiento"),
    USER_ALIAS_NOT_AVAILABLE("Alias de usuario no disponible"),
    USER_EMAIL_PREVIOUSLY_REGISTERED("El email está siendo utilizado"),
    USER_NOT_FOUND("No se ha encontrado al usuario");

}
