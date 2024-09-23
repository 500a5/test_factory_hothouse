package soft.divan.test_factory_hothouse.domain.usecases

import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class RegistrationUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String, name: String, userName: String): Rezult<Boolean> {
        return authRepository.registrationUser(phone, name, userName)
    }
}