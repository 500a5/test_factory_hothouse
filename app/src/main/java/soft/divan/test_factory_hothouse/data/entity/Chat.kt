package soft.divan.test_factory_hothouse.data.entity


data class Chat(
    var id: String = "",
    var name: String = "",
    var messages: List<Message> = mutableListOf(),
)