package soft.divan.test_factory_hothouse.data.local.source.impl


import soft.divan.test_factory_hothouse.domain.model.Card
import soft.divan.test_factory_hothouse.data.local.database.dao.CardDao
import soft.divan.test_factory_hothouse.data.local.database.entity.CardEntity
import soft.divan.test_factory_hothouse.domain.utils.Rezult
import soft.divan.test_factory_hothouse.domain.utils.safeResult
import soft.divan.world.words.data.source.CardDataSource


class CardLocalDataSourceImpl(private val dao: CardDao) : CardDataSource {

    override suspend fun save(card: Card): Rezult<Unit> = safeResult {
        dao.save(CardEntity.mappingFromData(card))
    }

    override suspend fun delete(id: String): Rezult<Unit> = safeResult { dao.delete(id) }

    override suspend fun get(id: String): Rezult<Card?> = safeResult { dao.get(id)?.mappingToDomain() }

    override suspend fun getAll(): Rezult<List<Card>> = safeResult { dao.getAll().map { it.mappingToDomain() } }

    override suspend fun saveAll(entities: List<Card>): Rezult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Rezult<Unit> = safeResult { dao.deleteAll() }
}