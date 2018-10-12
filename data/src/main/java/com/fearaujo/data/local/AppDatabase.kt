package com.fearaujo.data.local

import androidx.room.RoomDatabase
import androidx.room.Database
import com.fearaujo.model.Venue


@Database(entities = [Venue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): LocalRepository
}