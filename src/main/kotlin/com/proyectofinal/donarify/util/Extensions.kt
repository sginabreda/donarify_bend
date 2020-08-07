package com.proyectofinal.donarify.util

import java.text.SimpleDateFormat
import java.util.Date

fun Date.toStringDate(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(this)
}
