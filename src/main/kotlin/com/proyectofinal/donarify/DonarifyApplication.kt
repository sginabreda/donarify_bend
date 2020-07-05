package com.proyectofinal.donarify

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DonarifyApplication

fun main(args: Array<String>) {
	runApplication<DonarifyApplication>(*args)
}

fun <R : Any> R.logger(): Lazy<Logger> {
	return lazy { LoggerFactory.getLogger(this.javaClass) }
}
