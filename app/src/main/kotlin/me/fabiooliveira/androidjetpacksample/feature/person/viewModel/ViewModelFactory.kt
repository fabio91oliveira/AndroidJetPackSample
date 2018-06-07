package me.fabiooliveira.androidjetpacksample.feature.person.viewModel

import javax.inject.Singleton
import android.arch.lifecycle.ViewModel
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProvider
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository


@Singleton
class ViewModelFactory @Inject constructor(private val repository: PersonRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PersonViewModel::class.java))
            PersonViewModel(repository) as T
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}