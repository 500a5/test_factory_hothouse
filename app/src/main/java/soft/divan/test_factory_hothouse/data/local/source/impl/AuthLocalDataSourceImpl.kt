package soft.divan.test_factory_hothouse.data.local.source.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeResult
import soft.divan.world.words.data.local.source.AuthLocalDataSource


class AuthLocalDataSourceImpl(context: Context) : AuthLocalDataSource {

    companion object {
        private val Context.dataStore by preferencesDataStore("auth_preferences")

        private val IS_AUTHENTICATED_KEY = stringPreferencesKey("is_authenticated")
        private val DATA_AUTHENTICATION_KEY = stringPreferencesKey("data_authentication")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    private val dataStore = context.dataStore

    //todo return rezulst
    override suspend fun saveAuthState(authenticationState: AuthenticationState) {
        safeResult {
            dataStore.edit { preferences ->
                preferences[IS_AUTHENTICATED_KEY] = authenticationState.name
            }
        }
    }

    override suspend fun saveUserId(userId: String) {
        safeResult {
            dataStore.edit { preferences ->
                preferences[USER_ID_KEY] = userId
            }
        }
    }

    override suspend fun getUserUuid(): Rezult<String> {
        return safeResult {
            dataStore.data.map { preferences ->
                preferences[USER_ID_KEY]
            }.first()!!
        }
    }

    override suspend fun isAuthenticate(): Rezult<AuthenticationState> = safeResult {
        dataStore.data.map { preferences ->
            val name = preferences[IS_AUTHENTICATED_KEY] ?: AuthenticationState.UNAUTHENTICATED.name
            AuthenticationState.valueOf(name)
        }.first()
    }
    //todo return rezulst

    override suspend fun clearAuthData() {

        safeResult {
            dataStore.edit { preferences -> preferences.remove(IS_AUTHENTICATED_KEY) }

            dataStore.edit { preferences -> preferences.remove(DATA_AUTHENTICATION_KEY) }

            dataStore.edit { preferences -> preferences.remove(USER_ID_KEY) }


        }
    }

    override suspend fun saveDateAuthentication(dataAuth: String): Rezult<Unit> {
        return when (safeResult {
            dataStore.edit { preferences ->
                preferences[DATA_AUTHENTICATION_KEY] = dataAuth
            }
        }) {
            is Rezult.Error -> Rezult.Error(Reazon.DATABASE_ERROR)
            is Rezult.Success -> Rezult.Success(Unit)
        }
    }

    //todo return rezulst
    override suspend fun getDateAuthentication(): Rezult<String> = safeResult {
        dataStore.data.map { preferences ->
            preferences[DATA_AUTHENTICATION_KEY]
        }.first()!!
    }

}

