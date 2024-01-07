package br.com.spolaorthays.filmoteca.shared.extensions

import br.com.spolaorthays.filmoteca.shared.model.Constants.NOT_AVAILABLE
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun formatBRDate(originalDate: String): String {
    val originalDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val neededDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return try {
        val parserDate = originalDateFormat.parse(originalDate)
        return neededDateFormat.format(parserDate)
    } catch (_: Exception) {
        NOT_AVAILABLE
    }
}

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