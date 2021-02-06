package com.example.colormemory.di.home

import androidx.lifecycle.ViewModel
import com.example.colormemory.di.ViewModelKey
import com.example.colormemory.view.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}