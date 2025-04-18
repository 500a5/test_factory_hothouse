package soft.divan.test_factory_hothouse.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import soft.divan.test_factory_hothouse.data.local.database.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categoryTable WHERE documentId = :documentId")
    suspend fun getById(documentId: String): CategoryEntity?

    @Query("SELECT * FROM categoryTable WHERE name = :name")
    suspend fun getByName(name: String): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CategoryEntity)

    @Query("DELETE FROM categoryTable WHERE documentId = :documentId")
    fun delete(documentId: String)

    @Query("SELECT * FROM categoryTable")
    fun getAll(): List<CategoryEntity>

    @Query("DELETE FROM categoryTable")
    fun deleteAll()
}