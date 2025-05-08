package com.tekri.grunts.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tekri.grunts.model.Profile
import com.tekri.grunts.model.User

@Database(entities = [Profile::class, User::class], version = 3, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(context: Context): ProfileDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}