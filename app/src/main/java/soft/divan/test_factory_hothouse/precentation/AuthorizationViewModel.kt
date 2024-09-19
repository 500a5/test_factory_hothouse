package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase


class AuthorizationViewModel(private val sendAuthCodeUseCase: SendAuthCodeUseCase) : ViewModel() {
    fun sendAuthCode(phone: String){
        viewModelScope.launch {
            sendAuthCodeUseCase(phone)
        }
    }
}