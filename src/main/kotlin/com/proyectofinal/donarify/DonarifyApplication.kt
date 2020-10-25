package com.proyectofinal.donarify

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.core.ParameterizedTypeReference

@SpringBootApplication
@ConfigurationPropertiesScan
class DonarifyApplication

fun main(args: Array<String>) {
    runApplication<DonarifyApplication>(*args)
}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(this.javaClass) }
}
