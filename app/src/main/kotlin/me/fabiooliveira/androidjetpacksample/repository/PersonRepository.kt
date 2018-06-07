package me.fabiooliveira.androidjetpacksample.repository

import io.reactivex.Flowable
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao
import javax.inject.Inject

class PersonRepository @Inject constructor(private val personDao: PersonDao) {

    fun createPerson(person: Person): Long {
        return personDao.insert(person)
    }

    fun getAll(): Flowable<List<Person>> {
        return personDao.findAll()
    }
}