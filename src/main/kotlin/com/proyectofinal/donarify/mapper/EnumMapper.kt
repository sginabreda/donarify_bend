package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.PostType
import com.proyectofinal.donarify.exception.BadRequestException

class EnumMapper {
    companion object {
        val postTypeMapper = mapOf(
            "JOB_OFFER" to PostType.JOB_OFFER,
            "VOLUNTEERING" to PostType.VOLUNTEERING
        )

        val booleanMapper = mapOf(
            "true" to true,
            "false" to false
        )

        fun <T> throwError(param: String): T {
            throw BadRequestException("Invalid value for parameter: $param")
        }
    }
}
