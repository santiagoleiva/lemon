package me.lemon.challenge.config

enum class ErrorCatalog(
    val defaultMessage: String
) {

    USER_ALIAS_NOT_AVAILABLE("Alias de usuario no disponible"),
    USER_EMAIL_PREVIOUSLY_REGISTERED("El email está siendo utilizado"),
    INTERNAL_ERROR("Ha ocurrido un error inesperado"),
    UNPROCESSABLE_ENTITY("No es posible procesar la entidad");

}
