package me.fabiooliveira.androidjetpacksample

import android.app.Application
import me.fabiooliveira.androidjetpacksample.di.component.DaggerMainApplicationComponent
import me.fabiooliveira.androidjetpacksample.di.component.MainApplicationComponent
import me.fabiooliveira.androidjetpacksample.di.module.MainApplicationModule
import me.fabiooliveira.androidjetpacksample.di.module.RoomModule

class MainApplication: Application() {

    lateinit var mainAppComponent: MainApplicationComponent

    override fun onCreate() {
        super.onCreate()

        mainAppComponent = DaggerMainApplicationComponent
                .builder()
                .mainApplicationModule(MainApplicationModule(this))
                .roomModule(RoomModule(this))
                .build()
    }

}