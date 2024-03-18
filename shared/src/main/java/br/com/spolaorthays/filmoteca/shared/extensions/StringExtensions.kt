package br.com.spolaorthays.filmoteca.shared.extensions

import br.com.spolaorthays.filmoteca.shared.model.Constants.MOVIE_API_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.NOT_AVAILABLE
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDollar(budget: Long): String {
    val numberFormatUS = NumberFormat.getCurrencyInstance(Locale.US)
    val formatLongValue = numberFormatUS.format(budget)
    val finalValue = formatLongValue.substring(1, formatLongValue.length)
    return if (finalValue == "0.00") {
        NOT_AVAILABLE
    } else {
        finalValue
    }
}

fun formatReal(value: Double): String {
    val numberFormatBR = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val formatLongValue = numberFormatBR.format(value)
    val finalValue = formatLongValue.substring(3, formatLongValue.length)
    return if (finalValue == "0.00") {
        NOT_AVAILABLE
    } else {
        finalValue
    }
}

fun genericDateFormatter(date: String, originalFormat: String, neededFormat: String): String {
    val originalDateFormat = SimpleDateFormat(originalFormat, Locale.getDefault())
    val neededDateFormat = SimpleDateFormat(neededFormat, Locale.getDefault())

    return try {
        val parserDate = originalDateFormat.parse(date)
        return parserDate?.let { neededDateFormat.format(it) } ?: NOT_AVAILABLE
    } catch (_: Exception) {
        NOT_AVAILABLE
    }
}

fun formatterCalendarDate(actualDate: Date): String {
    val formatter = SimpleDateFormat(MOVIE_API_FORMAT, Locale.getDefault())
    return formatter.format(actualDate)
}