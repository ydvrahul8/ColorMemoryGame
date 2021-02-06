package com.example.colormemory.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colormemory.models.CardDetails
import com.example.colormemory.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    val mScore = MutableLiveData<Int>(0)
    val score: LiveData<Int> get() = mScore

    private val _firstCardDetails = MutableLiveData<Resource<CardDetails>>()
    val firstCardDetails: LiveData<Resource<CardDetails>> get() = _firstCardDetails

    private val _secondCardDetails = MutableLiveData<Resource<CardDetails>>()
    val secondCardDetails: LiveData<Resource<CardDetails>> get() = _secondCardDetails

    fun checkCards(firstCards: CardDetails?, secondCards: CardDetails?) {
        CoroutineScope(Dispatchers.Main).launch {
            if (firstCards?.colorCards?.colorTag.equals(secondCards?.colorCards?.colorTag)) {
                delay(500)
                mScore.value = mScore.value!! + 2
                delay(1000)
                _firstCardDetails.value =
                    Resource.matched(firstCards!!)
                _secondCardDetails.value =
                    Resource.matched(
                        secondCards!!
                    )
            } else {
                delay(500)
                mScore.value = mScore.value!! - 1
                delay(1000)
                _firstCardDetails.value =
                    Resource.unmatched(
                        firstCards!!
                    )
                _secondCardDetails.value =
                    Resource.unmatched(
                        secondCards!!
                    )
            }
        }
    }

    fun resetScore() {
        mScore.value = 0
    }
}