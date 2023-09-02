package com.app.fitspace.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        fun formatDate(date: Date): String {
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        }
    }
}