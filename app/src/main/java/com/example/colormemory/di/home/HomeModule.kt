package com.example.colormemory.di.home

import com.bumptech.glide.RequestManager
import com.example.colormemory.view.common.adapter.MyImageAdapter
import dagger.Module
import dagger.Provides

@Module
object HomeModule {

    @HomeScope
    @Provides
    @JvmStatic
    fun provideAdapter(requestManager:RequestManager): MyImageAdapter {
        return MyImageAdapter(requestManager)
    }

}