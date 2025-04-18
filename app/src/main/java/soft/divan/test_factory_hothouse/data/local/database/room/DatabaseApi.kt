package soft.divan.test_factory_hothouse.data.local.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import soft.divan.test_factory_hothouse.data.local.database.dao.CardDao
import soft.divan.test_factory_hothouse.data.local.database.entity.CardEntity
import soft.divan.test_factory_hothouse.data.local.database.dao.CategoryDao
import soft.divan.test_factory_hothouse.data.local.database.entity.CategoryEntity
import soft.divan.test_factory_hothouse.data.local.database.room.utils.DatabaseConverters


@Database(entities = [CategoryEntity::class, CardEntity::class], version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class DatabaseApi : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun cardDao(): CardDao
}