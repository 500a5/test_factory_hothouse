package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult


class AuthorizationViewModel(private val sendAuthCodeUseCase: SendAuthCodeUseCase) : ViewModel() {
    private val _sendAuthCode = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sendAuthCode: SharedFlow<Boolean>  = _sendAuthCode.asSharedFlow()

    fun sendAuthCode(phone: String){
        viewModelScope.launch() {
            when (val result = sendAuthCodeUseCase(phone)){
                is Rezult.Error -> TODO()
                is Rezult.Success -> {_sendAuthCode.tryEmit(result.data) }
            }
        }
    }
}