package com.example.bulletjournal

import androidx.lifecycle.*
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Word
import com.example.bulletjournal.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val wordText = MutableLiveData<String>()
    val isAddButtonEnable = wordText.map { !it.isNullOrEmpty() }

    private lateinit var repository: WordRepository
    lateinit var allWords: LiveData<List<Word>>

    fun init(wordDao: WordDao) {
        repository = WordRepository(wordDao)
        allWords = repository.allWords
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(Word(word = wordText.value!!))
        wordText.postValue("")
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
