package com.example.kotlincountries.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryModel::class], version = 1)
abstract class CountryDB : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {

        /*
        This line declares a volatile variable named instanceDB which holds an instance of the CountryDB class.
        The volatile keyword ensures that changes made to this variable are immediately visible to other threads.
         */
        @Volatile
        private var instanceDB: CountryDB? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instanceDB ?: synchronized(lock) {
            instanceDB?: makeDB(context).also {
                instanceDB = it
            }
        }

        private fun makeDB(context: Context) = Room.databaseBuilder(
            context.applicationContext, CountryDB::class.java, "countryDB"
        ).build()

    }

}