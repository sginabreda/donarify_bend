package com.proyectofinal.donarify.domain

enum class VolunteeringType(val value: Long) {
    COMMON(0),
    CORPORATE(1);

    companion object {
        fun getVolunteeringType(value: Long): VolunteeringType {
            return values().first { v -> v.value == value }
        }
    }
}
