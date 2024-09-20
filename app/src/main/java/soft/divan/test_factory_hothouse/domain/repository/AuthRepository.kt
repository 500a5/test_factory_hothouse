package soft.divan.test_factory_hothouse.domain.repository

import soft.divan.test_factory_hothouse.domain.api.AuthServiceApi
import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.utils.Rezult

interface AuthRepository {
    suspend fun sendAuthCode(phone: String): Rezult<Boolean>
}