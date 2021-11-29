package me.lemon.challenge.config

enum class ErrorCatalog(
    val defaultMessage: String
) {

    INTERNAL_ERROR("Ha ocurrido un error inesperado"),
    UNPROCESSABLE_ENTITY("No es posible procesar la entidad");

}
