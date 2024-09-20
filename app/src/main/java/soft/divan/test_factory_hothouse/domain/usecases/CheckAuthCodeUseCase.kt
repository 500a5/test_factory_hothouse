package soft.divan.test_factory_hothouse.domain.usecases

import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class CheckAuthCodeUseCase(private val  authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String, otpCode: String): Rezult<Boolean> {
        return  authRepository.checkAuthCodeUseCase(phone, otpCode)
    }
}