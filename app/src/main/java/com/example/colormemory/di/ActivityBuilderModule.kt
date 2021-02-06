package com.example.colormemory.di

import com.example.colormemory.di.highscore.HighScoreModule
import com.example.colormemory.di.highscore.HighScoreScope
import com.example.colormemory.di.highscore.HighScoreViewModelModule
import com.example.colormemory.di.home.HomeModule
import com.example.colormemory.di.home.HomeScope
import com.example.colormemory.di.home.HomeViewModelModule
import com.example.colormemory.view.highscore.HighScoreActivity
import com.example.colormemory.view.home.HomeActivity
import com.example.colormemory.view.home.HomeViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(
        modules = [
            HomeViewModelModule::class,
            HighScoreViewModelModule::class,
            HomeModule::class]
    )
    abstract fun contributeHomeActivity(): HomeActivity

    @HighScoreScope
    @ContributesAndroidInjector(
        modules = [
            HighScoreViewModelModule::class,
            HighScoreModule::class]
    )
    abstract fun contributeHighScoreActivity(): HighScoreActivity

}