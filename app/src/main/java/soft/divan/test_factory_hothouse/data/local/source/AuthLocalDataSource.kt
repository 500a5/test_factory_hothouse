package soft.divan.world.words.data.local.source

import soft.divan.world.words.domain.enums.AuthenticationState
import soft.divan.world.words.domain.utils.Rezult

interface AuthLocalDataSource {
    suspend fun saveAuthState(authenticationState: AuthenticationState)
    suspend fun saveUserId(userId: String)
    suspend fun getUserUuid(): Rezult<String>
    suspend fun isAuthenticate(): Rezult<AuthenticationState>
    suspend fun clearAuthData()
    suspend fun saveDateAuthentication(dataAuth: String):Rezult<Unit>
    suspend fun getDateAuthentication(): Rezult<String>
}
