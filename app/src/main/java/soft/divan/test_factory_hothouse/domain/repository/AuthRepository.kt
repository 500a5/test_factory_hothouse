package soft.divan.test_factory_hothouse.domain.repository

import soft.divan.test_factory_hothouse.domain.utils.Rezult

interface AuthRepository {
    suspend fun sendAuthCode(phone: String): Rezult<Boolean>
    suspend fun checkAuthCodeUseCase(phone: String, otpCode: String): Rezult<Boolean>
    suspend fun registrationUser(phone: String, name: String, userName: String): Rezult<Unit>


}