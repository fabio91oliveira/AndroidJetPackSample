package me.fabiooliveira.androidjetpacksample.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(val name: String,
        val description: String,
        val avatar: Int) {
        @PrimaryKey(autoGenerate = true) var id: Long = 0
}