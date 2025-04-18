package soft.divan.world.words.data.source

import soft.divan.world.words.data.remote.source.CardRemoteDataSource
import soft.divan.world.words.domain.enums.AuthenticationState
import soft.divan.world.words.domain.repository.AuthRepository
import soft.divan.world.words.domain.utils.Rezult


class CardDataSourceSelector(
    internal val cardLocalDataSource: CardDataSource,
    internal val cardRemoteDataSource: CardRemoteDataSource,
    private val authRepository: AuthRepository
) {
    suspend fun getDataSource(): CardDataSource {
        return when (val res = authRepository.isUserAuthenticated()) {
            is Rezult.Error -> cardLocalDataSource
            is Rezult.Success -> when (res.data) {
                AuthenticationState.AUTHENTICATED -> cardRemoteDataSource
                AuthenticationState.UNAUTHENTICATED -> cardLocalDataSource
            }
        }
    }
}

