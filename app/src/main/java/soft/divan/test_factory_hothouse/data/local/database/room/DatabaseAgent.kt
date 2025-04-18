package soft.divan.test_factory_hothouse.data.local.database.room

import android.app.Application
import androidx.room.Room


class DatabaseAgent {
    companion object {
        fun api(app: Application): DatabaseApi {
            return Room.databaseBuilder(app, DatabaseApi::class.java, "bd")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}