package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.CheckAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.usecases.GetCurrentUserUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class CheckAuthCodeViewModel(private val checkAuthCodeUseCase: CheckAuthCodeUseCase) : ViewModel() {
    private val _checkAuthCode = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val checkAuthCode: SharedFlow<Boolean> = _checkAuthCode.asSharedFlow()
    fun checkAuthCode(phone: String, otpCode: String){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = checkAuthCodeUseCase(phone, otpCode)){
                is Rezult.Error -> {}
                is Rezult.Success -> {_checkAuthCode.tryEmit(result.data) }
            }
        }
    }


}