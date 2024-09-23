package soft.divan.test_factory_hothouse.precentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import soft.divan.test_factory_hothouse.domain.usecases.RegistrationUserUseCase
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class RegistrationViewModel(private val registrationUserUseCase: RegistrationUserUseCase) : ViewModel() {

    private val _registrationUser = MutableSharedFlow<Rezult<Boolean>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val registrationUser: SharedFlow<Rezult<Boolean>> = _registrationUser.asSharedFlow()

    fun registrationUser(phone: String, name: String, userName: String ) {
        viewModelScope.launch() {
            _registrationUser.tryEmit(registrationUserUseCase(phone, name, userName))
            when (val result = registrationUserUseCase(phone, name, userName)) {
                is Rezult.Error -> TODO()
                is Rezult.Success -> {
                    _registrationUser.tryEmit(result)
                }
            }
        }
    }

}