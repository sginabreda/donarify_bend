package com.proyectofinal.donarify.context

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class ContextHelper {
    companion object {
        fun getLoggedUser(): String {
            return SecurityContextHolder.getContext().authentication.name
        }
    }
}
