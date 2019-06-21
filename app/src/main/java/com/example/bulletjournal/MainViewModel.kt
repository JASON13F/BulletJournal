package com.example.bulletjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bulletjournal.database.WordRoomDatabase
import com.example.bulletjournal.enums.Word
import com.example.bulletjournal.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repository =
        WordRepository(WordRoomDatabase.getDatabase(app).wordDao())
    val allWords: LiveData<List<Word>> = repository.allWords

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
