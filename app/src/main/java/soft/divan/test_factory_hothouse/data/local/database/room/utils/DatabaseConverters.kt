package soft.divan.test_factory_hothouse.data.local.database.room.utils


import androidx.room.TypeConverter

object DatabaseConverters {
    private const val UNIQUE_SING = "?:*№"

    @TypeConverter
    fun stringToListString(data: String): List<String> {
        return if (data == "") return listOf() else data.split(UNIQUE_SING)
    }

    @TypeConverter
    fun listStringToString(data: List<String>): String {
        return data.joinToString(UNIQUE_SING)
    }

}