package soft.divan.test_factory_hothouse.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import soft.divan.test_factory_hothouse.data.local.database.entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM cardTable WHERE id = :documentId")
    suspend fun get(documentId: String): CardEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CardEntity)

    @Query("DELETE FROM cardTable WHERE id = :documentId")
    fun delete(documentId: String)

    @Query("SELECT * FROM cardTable")
    fun getAll(): List<CardEntity>

    @Query("DELETE FROM cardTable")
    fun deleteAll()
}