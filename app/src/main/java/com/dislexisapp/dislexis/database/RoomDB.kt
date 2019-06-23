package com.dislexisapp.dislexis.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dislexisapp.dislexis.database.daos.UserDAO
import com.dislexisapp.dislexis.database.entities.User

@Database(entities = [User::class], version = 2, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomDB::class.java, "User_database")
                    .build()
                INSTANCE=instance
                return instance
            }

        }

    }

}