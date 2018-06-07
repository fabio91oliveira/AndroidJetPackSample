package me.fabiooliveira.androidjetpacksample.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import me.fabiooliveira.androidjetpacksample.entity.Person

@Dao
interface PersonDao {
    @Insert(onConflict = REPLACE) fun insert(person: Person): LiveData<Long>
    @Query("SELECT * FROM persons") fun findAll(): LiveData<List<Person>>
}