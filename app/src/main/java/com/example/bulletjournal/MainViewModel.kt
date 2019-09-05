package com.example.bulletjournal

import androidx.lifecycle.*
import com.example.bulletjournal.database.dao.WordDao
import com.example.bulletjournal.enums.Bullet
import com.example.bulletjournal.enums.Word
import com.example.bulletjournal.repository.WordRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val checkedId = MutableLiveData<Int>().apply { value = R.id.radio_task }
    val wordText = MutableLiveData<String>()
    val isAddButtonEnable = wordText.map { !it.isNullOrEmpty() }

    private lateinit var repository: WordRepository
    lateinit var allWords: LiveData<List<Word>>

    fun init(wordDao: WordDao) {
        repository = WordRepository(wordDao)
        allWords = repository.allWords
    }

    fun insert() = viewModelScope.launch {
        val text = wordText.value ?: return@launch
        val bullet = when (checkedId.value) {
            R.id.radio_task -> Bullet.TASK
            R.id.radio_memo -> Bullet.MEMO
            R.id.radio_event -> Bullet.EVENT
            else -> Bullet.TASK
        }

        repository.insert(Word(text = text, bullet = bullet))
        wordText.value = ""
    }

    fun update(id: Long, state: Boolean) = viewModelScope.launch {
        repository.update(id, state)
    }

    fun delete(word: Word) = viewModelScope.launch {
        repository.delete(word)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}
