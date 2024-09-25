package soft.divan.test_factory_hothouse.precentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState


class AuthorizationViewModel(private val sendAuthCodeUseCase: SendAuthCodeUseCase) : ViewModel() {

    var sendAuthCode = MutableStateFlow<UiState<Boolean>>(UiState.Init)

    fun sendAuthCode(phone: String) {
        sendAuthCode.value = UiState.Loading
        viewModelScope.launch() {
            sendAuthCode.value = when (val result = sendAuthCodeUseCase(phone)) {
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