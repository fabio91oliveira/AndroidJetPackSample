package me.fabiooliveira.androidjetpacksample.repository

import android.arch.lifecycle.LiveData
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao
import javax.inject.Inject

class PersonRepository @Inject constructor(val personDao: PersonDao) {

    fun createPerson(person: Person): LiveData<Long> {
        return personDao.insert(person)
    }

    fun getAll(): LiveData<List<Person>> {
        return personDao.findAll()
    }
}