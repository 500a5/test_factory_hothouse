package soft.divan.test_factory_hothouse.data.repositoryImp

import soft.divan.test_factory_hothouse.domain.api.ServerApi
import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.repository.MainRepository
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeHttpResult

class MainRepositoryImpl(private val api: ServerApi) : MainRepository {
    override suspend fun getCurrentUser(): Rezult<Boolean> {
            return when (val result = safeHttpResult { api.getCurrentUser() }) {
                is Rezult.Success -> {
                    Rezult.Success(true)
                }

                is Rezult.Error -> {
                    Rezult.Error(result.exception)
                }
            }
    }


}