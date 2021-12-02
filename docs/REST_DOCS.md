# Lemon 🍋 - Referencia de API

## Alta de usuario

`POST /api/v1/users`

### Cuerpo de petición

```json
{
  "firstname": "Alan",
  "lastname": "Castillo",
  "alias": "a.castillo",
  "email": "alan.castillo@lemon.me"
}
```

### Ejemplos de respuesta

- Usuario creado con éxito

`201 Created`

```json
{
  "id": 1,
  "firstname": "Alan",
  "lastname": "Castillo",
  "alias": "a.castillo",
  "email": "alan.castillo@lemon.me",
  "wallet": [
    {
      "code": "ARS",
      "amount": "0.00"
    },
    {
      "code": "USDT",
      "amount": "0.00"
    },
    {
      "code": "BTC",
      "amount": "0.00000000"
    }
  ]
}
```

- Respuestas para casos de error

`422 Unprocessable Entity`

```json
{
  "code": "USER_ALIAS_NOT_AVAILABLE",
  "message": "Alias de usuario no disponible"
}
```

```json
{
  "code": "USER_EMAIL_PREVIOUSLY_REGISTERED",
  "message": "El email está siendo utilizado"
}
```

## Obtención de usuario

`GET /api/v1/users/{userId}`

### Ejemplos de respuesta

- Petición exitosa

`200 OK`

```json
{
  "id": 1,
  "firstname": "Alan",
  "lastname": "Castillo",
  "alias": "a.castillo",
  "email": "alan.castillo@lemon.me",
  "wallet": [
    {
      "code": "ARS",
      "amount": "0.00"
    },
    {
      "code": "USDT",
      "amount": "0.00"
    },
    {
      "code": "BTC",
      "amount": "0.00000000"
    }
  ]
}
```

- Respuestas para casos de error

`404 Not Found`

```json
{
  "code": "USER_NOT_FOUND",
  "message": "No se ha encontrado al usuario"
}
```

## Registrar movimiento

`POST /api/v1/users/1/movements`

### Cuerpo de petición

```json
{
  "currency_code": "ARS",
  "movement_type": "DEPOSIT",
  "amount": 100
}
```

### Ejemplos de respuesta

- Movimiento registrado con éxito

`200 OK`

```json
{
  "currency": "ARS",
  "type": "DEPOSIT",
  "amount": "100.00",
  "created_at": "2021-12-01T21:10:58.16646"
}
```

- Respuestas para casos de error

`400 Bad Request`

```json
{
  "code": "INVALID_CURRENCY",
  "message": "La moneda es inválida"
}
```

```json
{
  "code": "INVALID_MOVEMENT_TYPE",
  "message": "El tipo de movimiento es inválido"
}
```

`422 Unprocessable Entity`

```json
{
  "code": "UNPROCESSABLE_MOVEMENT",
  "message": "No es posible procesar el movimiento"
}
```

## Listar movimientos de usuario

`GET /api/v1/users/1/movements`

### Parámetros de consulta

|Parámetro|Tipo|Valores posibles|
|---|---|---|
|currency|String|**ARS** - **USDT** - **BTC**|
|type|String|**DEPOSIT** - **WITHDRAW**|
|limit|Integer|10 (Valor por defecto)|
|offset|Integer|0 (Valor por defecto)|

### Ejemplos de respuesta

- Petición exitosa

`200 OK`

```json
[
  {
    "id": 7,
    "currency": "ARS",
    "type": "DEPOSIT",
    "amount": "50.00",
    "created_at": "2021-12-01T21:20:30"
  },
  {
    "id": 6,
    "currency": "ARS",
    "type": "DEPOSIT",
    "amount": "50.00",
    "created_at": "2021-12-01T21:20:29"
  },
  {
    "id": 5,
    "currency": "ARS",
    "type": "DEPOSIT",
    "amount": "50.00",
    "created_at": "2021-12-01T21:20:27"
  }
]
```

- Respuestas para casos de error

`500 Internal Server Error`

```json
{
  "code": "INTERNAL_ERROR",
  "message": "Ha ocurrido un error inesperado"
}
```
