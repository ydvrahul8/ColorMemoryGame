package com.example.colormemory.di

import android.app.Application
import android.graphics.drawable.Drawable
import android.service.autofill.UserData
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.colormemory.R
import com.example.colormemory.db.UserDAO
import com.example.colormemory.db.UserDatabase
import com.example.colormemory.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions().placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.card_bg)!!
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserDAO(application: Application):UserDAO{
        return UserDatabase.getInstance(application).userDAO
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserRepository(dao:UserDAO):UserRepository{
        return UserRepository(dao)
    }
}