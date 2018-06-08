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
class RepositoryModule {

    @Provides
    @Singleton
    fun providePersonRepository(personDao: PersonDao): PersonRepository{
        return PersonRepository(personDao)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: PersonRepository): ViewModelProvider.Factory {
        return ViewModelFactory(repository)
    }
}