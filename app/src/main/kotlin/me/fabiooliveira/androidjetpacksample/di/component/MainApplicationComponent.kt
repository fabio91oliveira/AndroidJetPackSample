package me.fabiooliveira.androidjetpacksample.di.component

import android.app.Application
import dagger.Component
import me.fabiooliveira.androidjetpacksample.di.module.MainApplicationModule
import me.fabiooliveira.androidjetpacksample.di.module.RoomModule
import me.fabiooliveira.androidjetpacksample.feature.person.ui.PersonFragment
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao
import me.fabiooliveira.androidjetpacksample.persistence.source.Database
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [(MainApplicationModule::class), (RoomModule::class)])
interface MainApplicationComponent {
    fun inject(personFragment: PersonFragment)
    fun application(): Application
    fun personDao(): PersonDao
    fun personDatabase(): Database
    fun personRepository(): PersonRepository
}