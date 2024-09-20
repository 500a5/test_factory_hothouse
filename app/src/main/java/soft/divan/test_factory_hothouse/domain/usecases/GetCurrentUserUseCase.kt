package soft.divan.test_factory_hothouse.domain.usecases

import soft.divan.test_factory_hothouse.domain.repository.MainRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult

class GetCurrentUserUseCase (private val  mainRepository: MainRepository) {
    suspend operator fun invoke(): Rezult<Boolean> {
        return  mainRepository.getCurrentUser()
    }
}