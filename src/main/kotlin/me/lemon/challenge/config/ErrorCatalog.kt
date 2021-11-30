package me.lemon.challenge.config

enum class ErrorCatalog(
    val defaultMessage: String
) {

    CURRENCY_NOT_FOUND("No se ha encontrado la moneda"),
    INTERNAL_ERROR("Ha ocurrido un error inesperado"),
    INVALID_MOVEMENT_TYPE("El tipo de movimiento ea inválido"),
    RESOURCE_NOT_FOUND("No se ha encontrado el recurso solicitado"),
    UNPROCESSABLE_ENTITY("No es posible procesar la entidad"),
    USER_ALIAS_NOT_AVAILABLE("Alias de usuario no disponible"),
    USER_EMAIL_PREVIOUSLY_REGISTERED("El email está siendo utilizado"),
    USER_NOT_FOUND("No se ha encontrado al usuario");

}
