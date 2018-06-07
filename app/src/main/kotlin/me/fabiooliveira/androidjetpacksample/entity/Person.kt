package me.fabiooliveira.androidjetpacksample.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
        @PrimaryKey
        val id: Long,
        val name: String)