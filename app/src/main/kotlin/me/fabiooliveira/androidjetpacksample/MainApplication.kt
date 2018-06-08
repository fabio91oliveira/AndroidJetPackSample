package me.fabiooliveira.androidjetpacksample

import android.app.Application
import me.fabiooliveira.androidjetpacksample.di.component.DaggerMainApplicationComponent
import me.fabiooliveira.androidjetpacksample.di.component.MainApplicationComponent
import me.fabiooliveira.androidjetpacksample.di.module.MainApplicationModule
import me.fabiooliveira.androidjetpacksample.di.module.RepositoryModule
import me.fabiooliveira.androidjetpacksample.di.module.RoomModule

class MainApplication: Application() {

    private lateinit var mainAppComponent: MainApplicationComponent

    override fun onCreate() {
        super.onCreate()

        mainAppComponent = DaggerMainApplicationComponent
                .builder()
                .mainApplicationModule(MainApplicationModule(this))
                .roomModule(RoomModule(this))
                .repositoryModule(RepositoryModule())
                .build()
    }

    fun getMainApplication(): MainApplicationComponent {
        return mainAppComponent
    }

}