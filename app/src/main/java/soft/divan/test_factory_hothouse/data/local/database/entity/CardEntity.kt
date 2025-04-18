package soft.divan.test_factory_hothouse.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import soft.divan.test_factory_hothouse.domain.model.Card
import soft.divan.test_factory_hothouse.domain.utils.dateFormat


@Entity(tableName = "cardTable")
data class CardEntity(
    @PrimaryKey
    var id: String,
    val word: String,
    val translation: String,
    val category: String,
    val examples: List<String>,
    val isWordCard: Boolean,
    val isLearned: Boolean,
    val dateCreated: String,
    val language: String,
    val userId: String

) {
    fun mappingToDomain() = Card(
        id = id,
        word = word,
        translation = translation,
        category = category,
        examples = examples,
        isWordCard = isWordCard,
        isLearned = isLearned,
        dateCreated = dateFormat().parse(dateCreated),
        language = language,
        userId = userId
    )

    companion object {
        fun mappingFromData(card: Card) = CardEntity(
                id = card.id,
                word = card.word,
                translation = card.translation,
                category = card.category,
                examples = card.examples,
                isWordCard = card.isWordCard,
                isLearned = card.isLearned,
                dateCreated = dateFormat().format(card.dateCreated),
                language = card.language,
                userId = card.userId

            )

    }
}

