package br.com.spolaorthays.filmoteca.shared.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatBRDate(): String {
    val originalDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val neededDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return try {
        val parserDate = originalDateFormat.parse(this)
        return neededDateFormat.format(parserDate)
    } catch (_: Exception) {
        "Não disponível"
    }
}