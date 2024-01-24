package com.matheus.receitasapp.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.data.repository.PreferenceRepository
import com.matheus.receitasapp.presentation.settings.state.SwitchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
): ViewModel(){

    //private var _emailPasswordState = MutableStateFlow(EmailPasswordState())
    //val emailPasswordState get() = _emailPasswordState.asStateFlow()
    private var _switchState = MutableStateFlow(SwitchState())
    val switchState get() = _switchState.asStateFlow()

//    fun onEmailTextEntered(email: String) {
//        _emailPasswordState.update {
//            emailPasswordState.value.copy(
//                isValidEmail = email.isValidEmail(),
//                email = email
//            )
//        }
//    }

//    fun onPasswordEntered(password: String) {
//        _emailPasswordState.update {
//            emailPasswordState.value.copy(
//                isValidPassword = password.isValidPassword(),
//                password = password
//            )
//        }
//    }

    fun onSwitchChecked(isChecked: Boolean) {
        _switchState.update {
            switchState.value.copy(
                isChecked = isChecked
            )
        }
    }


    fun saveIsLoggedIn(isLoggedIn: Boolean) {
        viewModelScope.launch {
            preferenceRepository.saveUserLoggedInStatus(isLoggedIn)
        }
    }
}