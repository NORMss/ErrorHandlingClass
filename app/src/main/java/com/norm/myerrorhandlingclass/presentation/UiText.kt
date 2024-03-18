package com.norm.myerrorhandlingclass.presentation

import android.content.Context
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext

sealed class UiText {
    data class DynamicsString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicsString -> value
            is StringResource -> LocalContext.current.getString(id, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicsString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}