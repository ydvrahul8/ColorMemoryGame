package com.example.colormemory.view.highscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colormemory.models.User
import com.example.colormemory.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HighScoreViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val allUsers = repository.users

    private val _insertState = MutableLiveData<Long>()
    val insertState: LiveData<Long> get() = _insertState

    fun insert(user: User) {
        viewModelScope.launch {
            _insertState.value = repository.insert(user)
        }
    }
}