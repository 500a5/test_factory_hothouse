package soft.divan.test_factory_hothouse.precentation


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState


class AuthorizationViewModel(private val sendAuthCodeUseCase: SendAuthCodeUseCase) : ViewModel() {

    var sendAuthCode: UiState<Boolean> by mutableStateOf(UiState.Empty)

    fun sendAuthCode(phone: String) {
        sendAuthCode = UiState.Loading
        viewModelScope.launch() {
            sendAuthCode = when (val result = sendAuthCodeUseCase(phone)) {
                is Rezult.Error -> {
                    UiState.Error("error")
                }

                is Rezult.Success -> {
                    if (result.data)
                        UiState.Success(true)
                    else
                        UiState.Error("error")
                }
            }
        }
    }
}