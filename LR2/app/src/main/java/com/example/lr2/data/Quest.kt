package com.example.lr2.data

import androidx.annotation.StringRes

data class Quest(@StringRes val textResId: Int, val answer : Boolean)
// для хранения данных и автоматически предоставляет методы toString(), equals(), hashCode() и copy()