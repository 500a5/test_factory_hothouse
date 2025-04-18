package soft.divan.test_factory_hothouse.domain.model

data class UserInfo(
    val id: String,
    val aboutMe: String,
    val avatarImagePath: String,
    val avatarUrl: String,
    val learnLanguages: List<String>,
    val nativeLanguage: String,
    val username: String
)
