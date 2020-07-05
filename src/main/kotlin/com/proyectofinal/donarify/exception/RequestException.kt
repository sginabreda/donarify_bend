package com.proyectofinal.donarify.exception

import org.springframework.http.HttpStatus

class RequestException(
    override var message: String,
    var code: String,
    var status: Int
) : RuntimeException() {
    constructor() : this("", "", HttpStatus.INTERNAL_SERVER_ERROR.value())
}
