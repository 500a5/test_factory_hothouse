package soft.divan.test_factory_hothouse.domain.repository

import soft.divan.test_factory_hothouse.domain.utils.Rezult

interface MainRepository {
    suspend fun getCurrentUser(): Rezult<Boolean>

}