package soft.divan.world.words.data.source

import soft.divan.world.words.domain.model.Card
import soft.divan.world.words.domain.utils.Rezult

interface CardDataSource {
    suspend fun save(entity: Card): Rezult<Unit>
    suspend fun delete(id: String): Rezult<Unit>
    suspend fun get(id: String): Rezult<Card?>
    suspend fun getAll(): Rezult<List<Card>>
    suspend fun saveAll(entities: List<Card>): Rezult<Unit>
    suspend fun deleteAll(): Rezult<Unit>
}