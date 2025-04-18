package soft.divan.test_factory_hothouse.domain.model

import soft.divan.test_factory_hothouse.data.remote.entity.Message


data class Chat(
    var id: String = "",
    var name: String = "",
    var messages: List<Message> = mutableListOf(),
)