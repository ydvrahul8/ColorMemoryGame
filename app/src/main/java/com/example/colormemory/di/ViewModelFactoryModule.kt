package com.example.colormemory.di

import androidx.lifecycle.ViewModelProvider
import com.example.colormemory.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory

}