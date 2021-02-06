package com.example.colormemory.di.highscore

import androidx.lifecycle.ViewModel
import com.example.colormemory.di.ViewModelKey
import com.example.colormemory.view.highscore.HighScoreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HighScoreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HighScoreViewModel::class)
    abstract fun bindHighScoreViewModel(viewModel: HighScoreViewModel): ViewModel
}