package com.example.bulletjournal.repository

import androidx.lifecycle.LiveData
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun insert(word: Word) = wordDao.insert(word)

    suspend fun update(id: Long, state: Boolean) = wordDao.update(id, state)

    suspend fun delete(word: Word) = wordDao.delete(word)

    suspend fun deleteAll() = wordDao.deleteAll()
}
