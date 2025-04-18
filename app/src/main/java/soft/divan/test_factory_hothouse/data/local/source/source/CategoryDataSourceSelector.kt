package soft.divan.world.words.data.source

import soft.divan.world.words.domain.enums.AuthenticationState
import soft.divan.world.words.domain.repository.AuthRepository
import soft.divan.world.words.domain.utils.Rezult

//todo как мы понимаем какой сорс нужен ?? смотри di
class CategoryDataSourceSelector(
    internal val categoryLocalDataSource: CategoryDataSource,
    internal val categoryRemoteDataSource: CategoryDataSource,
    private val authRepository: AuthRepository
) {
    suspend fun getDataSource(): CategoryDataSource {
        return when (val res = authRepository.isUserAuthenticated()) {
            is Rezult.Error -> categoryLocalDataSource
            is Rezult.Success -> when (res.data) {
                AuthenticationState.AUTHENTICATED -> categoryRemoteDataSource
                AuthenticationState.UNAUTHENTICATED -> categoryLocalDataSource
            }
        }
    }
}

