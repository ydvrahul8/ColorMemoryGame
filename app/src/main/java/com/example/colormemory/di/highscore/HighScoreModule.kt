package com.example.colormemory.di.highscore

import com.example.colormemory.view.common.adapter.HighScoreAdapter
import dagger.Module
import dagger.Provides

@Module
object HighScoreModule {
    @HighScoreScope
    @Provides
    @JvmStatic
    fun provideAdapter(): HighScoreAdapter {
        return HighScoreAdapter()
    }
}