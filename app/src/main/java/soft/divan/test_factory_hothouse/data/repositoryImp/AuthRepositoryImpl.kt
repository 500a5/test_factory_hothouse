package soft.divan.test_factory_hothouse.data.repositoryImp

import soft.divan.test_factory_hothouse.domain.api.AuthServiceApi
import soft.divan.test_factory_hothouse.domain.api.ServerApi
import soft.divan.test_factory_hothouse.domain.entities.PhoneBase
import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeHttpResult

class AuthRepositoryImpl(private val api: AuthServiceApi): AuthRepository {
    override suspend fun sendAuthCode(phone: String): Rezult<Success> {
        return when (val result = safeHttpResult { api.sendVerificationCode(PhoneBase(phone)) }) {
            is Rezult.Success -> {
                Rezult.Success(result.data)
            }

            is Rezult.Error -> {
                Rezult.Error(result.exception)
            }
        }
    }
}