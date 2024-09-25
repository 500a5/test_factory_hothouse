package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.RegistrationUserUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState

class RegistrationViewModel(private val registrationUserUseCase: RegistrationUserUseCase) : ViewModel() {

    var registrationUser =  MutableStateFlow<UiState<Unit>>(UiState.Init)

    fun registrationUser(phone: String, name: String, userName: String ) {
        registrationUser.value = UiState.Init
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

