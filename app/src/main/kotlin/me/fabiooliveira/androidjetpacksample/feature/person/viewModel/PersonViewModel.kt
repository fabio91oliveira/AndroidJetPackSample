package me.fabiooliveira.androidjetpacksample.feature.person.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository

class PersonViewModel(private val repository: PersonRepository) : ViewModel() {
    val personListMutableLive = MutableLiveData<List<Person>>()
    val longMutableLive = MutableLiveData<Long>()

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{personListMutableLive.postValue(it)})
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
