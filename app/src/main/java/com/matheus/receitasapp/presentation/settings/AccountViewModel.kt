package com.matheus.receitasapp.presentation.settings

import androidx.lifecycle.ViewModel
import com.matheus.receitasapp.presentation.settings.state.SwitchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(): ViewModel(){

    private val _switchState = MutableStateFlow(SwitchState())
    val switchState get() = _switchState.asStateFlow()

    fun onCheckChange(isChecked: Boolean) {
        _switchState.update {
            switchState.value.copy(isChecked = isChecked)
        }
    }

}