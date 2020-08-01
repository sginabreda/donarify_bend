package com.proyectofinal.donarify.exception

class BadRequestException(errorMessage: String, cause: Throwable? = null) :
    IllegalArgumentException(errorMessage, cause)
