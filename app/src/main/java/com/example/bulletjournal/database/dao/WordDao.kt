package com.example.bulletjournal.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bulletjournal.enums.Word

@Dao
interface WordDao {

    // 並べ替え：ORDER BY text ASC
    @Query("SELECT * from work_table")
    fun getAllWords(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Query("UPDATE work_table SET state = :state WHERE id = :id")
    suspend fun update(id: Long, state: Boolean)

    @Query("DELETE FROM work_table")
    suspend fun deleteAll()
}
