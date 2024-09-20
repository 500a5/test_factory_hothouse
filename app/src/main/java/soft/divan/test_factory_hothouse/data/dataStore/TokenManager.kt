package soft.divan.test_factory_hothouse.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import soft.divan.test_factory_hothouse.data.entity.Tokens

class TokenManager(private val context: Context) {
    companion object {
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token_")
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "jwt_token")

    }


    fun getToken(): Flow<Tokens?> {
        val refreshToken: Flow<String?> =
            context.dataStore.data.map { preferences -> preferences[REFRESH_TOKEN] }
        val accessToken: Flow<String?> =
            context.dataStore.data.map { preferences -> preferences[ACCESS_TOKEN] }
        return combine(refreshToken, accessToken) { refreshToken, accessToken ->
            if (refreshToken != null && accessToken != null) {
                Tokens(refreshToken, accessToken)
            } else {
                null
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveToken(token: Tokens) {
        context.dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token.refreshToken
        }
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token.accessToken
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(REFRESH_TOKEN)
        }
        context.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
        }
    }

}