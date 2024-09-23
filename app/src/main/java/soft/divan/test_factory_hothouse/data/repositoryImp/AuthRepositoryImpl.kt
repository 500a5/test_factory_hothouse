package soft.divan.test_factory_hothouse.data.repositoryImp

import soft.divan.test_factory_hothouse.data.dataStore.TokenManager
import soft.divan.test_factory_hothouse.data.entity.Tokens
import soft.divan.test_factory_hothouse.domain.api.AuthServiceApi
import soft.divan.test_factory_hothouse.domain.entities.CheckAuthCode
import soft.divan.test_factory_hothouse.domain.entities.PhoneBase
import soft.divan.test_factory_hothouse.domain.entities.RegisterIn
import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeHttpResult

class AuthRepositoryImpl(private val api: AuthServiceApi, private val tokenManager: TokenManager) :
    AuthRepository {
    override suspend fun sendAuthCode(phone: String): Rezult<Boolean> {
        return when (val result =
            safeHttpResult { api.sendVerificationCode(PhoneBase("+79219999999")) }) {
            is Rezult.Success -> {
               Rezult.Success(result.data.isSuccess)
            }

            is Rezult.Error -> {
                Rezult.Error(result.exception)
            }
        }
    }

    override suspend fun checkAuthCodeUseCase(phone: String, otpCode: String): Rezult<Boolean> {
        return when (val result =
            safeHttpResult { api.checkAuthCode(CheckAuthCode("+79219999999", otpCode)) }) {
            is Rezult.Success -> {
                tokenManager.saveToken(
                    Tokens(
                        refreshToken = result.data.refreshToken,
                        accessToken = result.data.accessToken
                    )
                )
                Rezult.Success(result.data.isUserExists)
            }

            is Rezult.Error -> {
                Rezult.Error(result.exception)
            }
        }
    }

    override suspend fun registrationUser(phone: String, name: String, userName: String): Rezult<Unit> {
        return when (val result =
            safeHttpResult { api.registerUser(RegisterIn("+79219999999", name, userName)) }) {
            is Rezult.Success -> {
                tokenManager.saveToken(
                    Tokens(
                        refreshToken = result.data.refreshToken,
                        accessToken = result.data.accessToken
                    )
                )
                Rezult.Success(Unit)
            }

            is Rezult.Error -> {
                Rezult.Error(result.exception)
            }
        }
    }
}