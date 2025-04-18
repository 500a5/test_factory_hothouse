package soft.divan.test_factory_hothouse.domain.model

import java.util.Date

data class User(
    val email: String,
    val id: String,
    val isShowProfileInWorld: Boolean,
    val dateCreated: Date,
)
