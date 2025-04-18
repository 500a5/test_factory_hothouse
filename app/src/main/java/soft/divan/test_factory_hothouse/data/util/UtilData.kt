package soft.divan.test_factory_hothouse.data.util

import soft.divan.test_factory_hothouse.domain.model.Chat
import soft.divan.test_factory_hothouse.data.remote.entity.Message

object UtilData {
    val messages1 = listOf(
        Message("1", "Пока",false),
        Message("12", "ага",true),
        Message("12", "да",false)
    )
    val messages2 = listOf(
        Message("2", "Пока",false),
        Message("22", "ага",true),
        Message("22", "нет",false)
    )
    val messages3 = listOf(
        Message("3", "Пока",false),
        Message("32", "ага",true),
        Message("32", "да",false)
    )

    val listChats = listOf(
        Chat(
            "1",
            "Иван",
            messages1
            ), Chat(
            "2",
            "Стас",
            messages2,
        ), Chat(
            "3",
            "Влад",
            messages3,
        )
    )



}