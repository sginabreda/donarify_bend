package com.proyectofinal.donarify.exception

import com.proyectofinal.donarify.domain.ApiError
import com.proyectofinal.donarify.logger
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    private val exLogger by logger()

    @ExceptionHandler(value = [(RequestException::class)])
    fun handleRequestException(ex: RequestException, request: WebRequest): ResponseEntity<ApiError> {
        val errorDetails = ApiError(ex.message, ex.code)
        exLogger.error("Exception thrown - CODE: ${ex.code}  MESSAGE: \"${ex.message}\" STATUS: ${ex.status}")
        return ResponseEntity(errorDetails, HttpStatus.valueOf(ex.status))
    }

    override fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ApiError("One or more parameters is either missing or incorrect", "bad.request")
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}
