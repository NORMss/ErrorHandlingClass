package com.norm.myerrorhandlingclass.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.myerrorhandlingclass.domain.AuthRepository
import com.norm.myerrorhandlingclass.domain.Result
import com.norm.myerrorhandlingclass.domain.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository,
) : ViewModel() {
    private val eventChanel = Channel<UserEvent>()
    val events = eventChanel.receiveAsFlow()

    fun onRegisterClick(password: String) {
        when (val result = userDataValidator.validatorPassword(password)) {
            is Result.Error -> {
                when (result.error) {
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }

            is Result.Success -> {

            }
        }

        viewModelScope.launch {
            when (val result = repository.register(password)) {
                is Result.Error -> {
//                    when (result.error) {
//                        DataError.Network.REQUEST_TIMEOUT -> TODO()
//                        DataError.Network.TOO_MANY_REQUEST -> TODO()
//                        DataError.Network.NO_INTERNET -> TODO()
//                        DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
//                        DataError.Network.SERVER_ERROR -> TODO()
//                        DataError.Network.SERIALIZATION_ERROR -> TODO()
//                        DataError.Network.UNKNOWN -> TODO()
//                    }

                    val errorMessage = result.error.asUiText()
                    eventChanel.send(UserEvent.Error(errorMessage))
                }

                is Result.Success -> {
                    result.data
                }
            }
        }
    }
}

sealed interface UserEvent {
    data class Error(val error: UiText) : UserEvent
}