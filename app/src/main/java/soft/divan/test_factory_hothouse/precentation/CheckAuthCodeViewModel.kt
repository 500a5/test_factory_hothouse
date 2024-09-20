package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class CheckAuthCodeViewModel(private val sendAuthCodeUseCase: SendAuthCodeUseCase) : ViewModel() {
    private val _sendAuthCode = MutableStateFlow<Boolean>(false)
    val sendAuthCode  = _sendAuthCode.asStateFlow()
    fun sendAuthCode(phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = sendAuthCodeUseCase(phone)){
                is Rezult.Error -> TODO()
                is Rezult.Success -> {_sendAuthCode.tryEmit(result.data) }
            }
        }
    }
}