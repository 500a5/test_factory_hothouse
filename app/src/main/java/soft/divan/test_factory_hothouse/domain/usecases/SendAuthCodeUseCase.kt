package soft.divan.test_factory_hothouse.domain.usecases

import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.repository.MainRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class SendAuthCodeUseCase(private val  authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String): Rezult<Success> {
        return  authRepository.sendAuthCode(phone)
    }
}