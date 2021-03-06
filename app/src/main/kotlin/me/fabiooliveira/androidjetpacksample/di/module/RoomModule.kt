package me.fabiooliveira.androidjetpacksample.di.module

import dagger.Module
import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Singleton
import dagger.Provides
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao
import me.fabiooliveira.androidjetpacksample.persistence.source.Database
import android.arch.persistence.room.Room
import me.fabiooliveira.androidjetpacksample.feature.person.viewModel.ViewModelFactory
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository

@Module
class RoomModule (application: Application) {

    private val database: Database = Room.databaseBuilder(
            application,
            Database::class.java,
            "Person.db"
    ).build()

    @Provides
    @Singleton
    fun providePersonDatabase(): Database {
        return database
    }

    @Provides
    @Singleton
    fun providePersonDao(database: Database): PersonDao{
        return database.personDao()
    }
}