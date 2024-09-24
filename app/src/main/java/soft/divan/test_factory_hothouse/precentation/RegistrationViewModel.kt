package soft.divan.test_factory_hothouse.precentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.RegistrationUserUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState

class RegistrationViewModel(private val registrationUserUseCase: RegistrationUserUseCase) : ViewModel() {

    var registrationUser =  MutableStateFlow<UiState<Unit>>(UiState.Empty)

    fun registrationUser(phone: String, name: String, userName: String ) {
        registrationUser.value = UiState.Empty
        viewModelScope.launch() {
            registrationUser.value = when (registrationUserUseCase(phone, name, userName)) {
                is Rezult.Error -> UiState.Error("eroror")
                is Rezult.Success -> {
                    UiState.Success(Unit)

                }
            }
        }
    }

}

