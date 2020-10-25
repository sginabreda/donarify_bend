package com.proyectofinal.donarify.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@Component
data class MercadoPagoConfig(
    val config: Config
)

@ConstructorBinding
@ConfigurationProperties(prefix = "mp")
data class Config(
    val access_token: String,
    val error_url: String,
    val success_url: String
)
