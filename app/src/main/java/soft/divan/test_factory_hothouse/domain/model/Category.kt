package soft.divan.test_factory_hothouse.domain.model

import java.util.Date


data class Category(
    val id: String,
    val cardsCount: Int = 0,
    val name: String,
    val dateCreated: Date
)