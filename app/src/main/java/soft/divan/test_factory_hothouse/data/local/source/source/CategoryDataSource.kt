package soft.divan.world.words.data.source

import soft.divan.world.words.domain.model.Category
import soft.divan.world.words.domain.utils.Rezult

interface CategoryDataSource {
    suspend fun save(entity: Category): Rezult<Unit>
    suspend fun delete(id: String): Rezult<Unit>
    suspend fun getById(id: String): Rezult<Category?>
    suspend fun getAll(): Rezult<List<Category>>
    suspend fun getByName(name: String): Rezult<Category?>
    suspend fun saveAll(entities: List<Category>): Rezult<Unit>
    suspend fun deleteAll(): Rezult<Unit>
}