package com.norm.myerrorhandlingclass.presentation

import com.norm.myerrorhandlingclass.R
import com.norm.myerrorhandlingclass.domain.DataError
import com.norm.myerrorhandlingclass.domain.Result

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> TODO()
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.the_request_timed_out
        )

        DataError.Network.TOO_MANY_REQUEST -> UiText.StringResource(
            R.string.youve_hit_your_rate_limit
        )

        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.no_internet
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.payload_too_large
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.server_error
        )

        DataError.Network.SERIALIZATION_ERROR -> UiText.StringResource(
            R.string.serialization_error
        )

        DataError.Network.UNKNOWN -> UiText.StringResource(
            R.string.unknown
        )
    }
}

fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}