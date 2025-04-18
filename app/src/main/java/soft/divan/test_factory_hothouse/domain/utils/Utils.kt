package soft.divan.test_factory_hothouse.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateNowFormat(): String {
       return dateFormat().format(Date())
}

@Suppress("SimpleDateFormat")
fun dateFormat(): SimpleDateFormat{
    val dateFormat = SimpleDateFormat("dd.MM.yyyy, HH:mm:ss")
    return dateFormat
}

fun String.formatToDisplayDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return formatter.format(this)
}

fun convertDateToString(date: Date, format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(date)
}

//todo закенить dateFormat на это и в domain уйти к date() вместо стринг
fun String.toDate(): Date? {
    return try {
        val formatter = SimpleDateFormat("dd.MM.yyyy, HH:mm:ss", Locale.getDefault())
        formatter.parse(this)
    } catch (e: Exception) {
        null
    }
}