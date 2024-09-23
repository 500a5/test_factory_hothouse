package soft.divan.test_factory_hothouse.precentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.RegistrationUserUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState

class RegistrationViewModel(private val registrationUserUseCase: RegistrationUserUseCase) : ViewModel() {

    var registrationUser: UiState<Unit> by mutableStateOf(UiState.Empty)

    fun registrationUser(phone: String, name: String, userName: String ) {
        registrationUser = UiState.Empty
        viewModelScope.launch() {
            registrationUser = when (registrationUserUseCase(phone, name, userName)) {
                is Rezult.Error -> UiState.Error("eroror")
                is Rezult.Success -> {
                    UiState.Success(Unit)

                }
            }
        }
    }

}

