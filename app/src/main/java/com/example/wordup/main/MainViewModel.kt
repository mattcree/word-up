package com.example.wordup.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val wordUpService: WordUpService
) : ViewModel() {

    val word = ObservableField("")

    fun nextWord() = GlobalScope.launch(Dispatchers.IO) {
        try {
            val newWord = wordUpService.getWord()
            setNewWord(newWord)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun setNewWord(word: String) = withContext(Dispatchers.Main) {
        this@MainViewModel.word.set(word)
    }
}