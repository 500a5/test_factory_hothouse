package soft.divan.test_factory_hothouse.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import soft.divan.test_factory_hothouse.domain.model.Category
import soft.divan.test_factory_hothouse.domain.utils.dateFormat


@Entity(tableName = "categoryTable")
data class CategoryEntity(
    @PrimaryKey
    var documentId: String,
    var cardsCount: Int,
    var name: String,
    var dateCreated: String
) {
    fun mappingToDomain() = Category(
        documentId, cardsCount, name, dateFormat().parse(dateCreated)
    )

}
