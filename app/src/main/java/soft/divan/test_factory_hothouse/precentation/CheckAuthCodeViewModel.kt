package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.CheckAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.precentation.util.UiState

class CheckAuthCodeViewModel(private val checkAuthCodeUseCase: CheckAuthCodeUseCase) : ViewModel() {
    var checkAuthCode = MutableStateFlow<UiState<Boolean>>(UiState.Init)
    fun checkAuthCode(phone: String, otpCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            checkAuthCode.value = UiState.Loading
            checkAuthCode.value = when (val result = checkAuthCodeUseCase(phone, otpCode)) {
                is Rezult.Error -> {
                    UiState.Error("error")
                }

                is Rezult.Success -> {
                    UiState.Success(result.data)
                }
            }
        }
    }


}