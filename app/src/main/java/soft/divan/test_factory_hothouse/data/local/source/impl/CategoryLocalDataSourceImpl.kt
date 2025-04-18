package soft.divan.test_factory_hothouse.data.local.source.impl


import soft.divan.test_factory_hothouse.domain.model.Category

import soft.divan.test_factory_hothouse.data.local.database.dao.CategoryDao
import soft.divan.test_factory_hothouse.data.local.database.entity.CategoryEntity
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.dateFormat
import soft.divan.test_factory_hothouse.domain.utils.safeResult
import soft.divan.world.words.data.source.CategoryDataSource



class CategoryLocalDataSourceImpl(private val dao: CategoryDao) : CategoryDataSource {

    override suspend fun save(entity: Category): Rezult<Unit> = safeResult {
        dao.save(
            CategoryEntity(
                documentId = entity.id,
                cardsCount = entity.cardsCount,
                name = entity.name,
                dateCreated = dateFormat().format(entity.dateCreated),
            )
        )
    }

    override suspend fun delete(id: String): Rezult<Unit> = safeResult { dao.delete(id) }

    override suspend fun getById(id: String): Rezult<Category?> = safeResult { dao.getById(id)?.mappingToDomain() }

    override suspend fun getByName(name: String): Rezult<Category?> = safeResult { dao.getByName(name)?.mappingToDomain() }
    override suspend fun saveAll(entities: List<Category>): Rezult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Rezult<Unit> = safeResult { dao.deleteAll() }

    override suspend fun getAll(): Rezult<List<Category>> = safeResult { dao.getAll().map { it.mappingToDomain() } }
}