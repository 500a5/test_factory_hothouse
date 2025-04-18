package soft.divan.test_factory_hothouse.domain.usecases

import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class SendAuthCodeUseCase(private val  authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String): Rezult<Boolean> {
        return  authRepository.sendAuthCode(phone)
    }
}