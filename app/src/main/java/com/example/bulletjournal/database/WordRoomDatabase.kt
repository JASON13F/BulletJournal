package com.example.bulletjournal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Word

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        fun getDatabase(context: Context): WordRoomDatabase =
            synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, WordRoomDatabase::class.java, "Word_database"
                ).build()
            }
    }
}
