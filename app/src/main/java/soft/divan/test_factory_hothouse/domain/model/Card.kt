package soft.divan.test_factory_hothouse.domain.model

import java.util.Date

data class Card(
    val id: String,
    val word: String,
    val translation: String,
    val category: String,
    val examples: List<String>,
    val isWordCard: Boolean,
    val isLearned: Boolean,
    val dateCreated: Date,
    val language: String,
    val userId: String,
)