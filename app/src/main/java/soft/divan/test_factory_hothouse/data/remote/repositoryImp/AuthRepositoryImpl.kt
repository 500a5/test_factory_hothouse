package soft.divan.test_factory_hothouse.data.remote.repositoryImp

import soft.divan.test_factory_hothouse.data.local.source.impl.TokenManager
import soft.divan.test_factory_hothouse.data.remote.entity.Tokens
import soft.divan.test_factory_hothouse.data.remote.api.AuthServiceApi
import soft.divan.test_factory_hothouse.data.remote.entity.CheckAuthCode
import soft.divan.test_factory_hothouse.data.remote.entity.PhoneBase
import soft.divan.test_factory_hothouse.data.remote.entity.RegisterIn
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeHttpResult

class AuthRepositoryImpl(private val api: AuthServiceApi, private val tokenManager: TokenManager) :
    AuthRepository {
    override suspend fun sendAuthCode(phone: String): Rezult<Boolean> {
        return when (val result =
            safeHttpResult { api.sendVerificationCode(PhoneBase(phone)) }) {
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
            safeHttpResult { api.checkAuthCode(CheckAuthCode(phone, otpCode)) }) {
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
            safeHttpResult { api.registerUser(RegisterIn(phone, name, userName)) }) {
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