package me.fabiooliveira.androidjetpacksample.di.module

import dagger.Module
import android.app.Application
import dagger.Provides
import me.fabiooliveira.androidjetpacksample.MainApplication


@Module
class MainApplicationModule(private val mainApplication: MainApplication) {

    @Provides
    fun provideMainApplication(): MainApplication {
        return mainApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mainApplication
    }
}