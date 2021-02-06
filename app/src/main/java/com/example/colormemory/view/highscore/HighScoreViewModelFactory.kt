package com.example.colormemory.view.highscore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colormemory.repository.UserRepository

class HighScoreViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighScoreViewModel::class.java)) {
            return HighScoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}