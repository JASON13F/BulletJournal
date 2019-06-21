package com.example.bulletjournal.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bulletjournal.enums.Word

@Dao
interface WordDao {

    @Query("SELECT * from work_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Query("DELETE FROM work_table")
    suspend fun deleteAll()
}
