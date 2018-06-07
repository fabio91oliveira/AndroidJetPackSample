package me.fabiooliveira.androidjetpacksample.feature.person.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository

class PersonAddViewModel(private val repository: PersonRepository) : ViewModel() {
    val longMutableLive = MutableLiveData<Long>()
    private val compositeDisposable = CompositeDisposable()

    fun isFieldEmpty(text: String): Boolean {
        return text.isBlank()
    }

    fun savePerson(person: Person) {
        compositeDisposable.add(Observable.fromCallable { repository.createPerson(person) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {longMutableLive.postValue(it)})
    }

    fun onDestroy() {
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}
