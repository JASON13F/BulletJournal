package com.example.bulletjournal.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    @WorkerThread
    suspend fun deleteAll() {
        wordDao.deleteAll()
    }
}
