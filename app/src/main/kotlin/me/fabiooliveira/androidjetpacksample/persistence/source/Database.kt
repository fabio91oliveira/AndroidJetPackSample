package me.fabiooliveira.androidjetpacksample.persistence.source

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao

@Database(entities = [(Person::class)], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun personDao(): PersonDao
}