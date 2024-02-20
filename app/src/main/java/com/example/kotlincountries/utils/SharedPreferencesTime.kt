package com.example.kotlincountries.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesTime {

    companion object {

        const val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile private var instanceSP: SharedPreferencesTime? = null

        private val lock = Any()

        operator fun invoke(context: Context): SharedPreferencesTime = instanceSP ?: synchronized(lock) {
            instanceSP ?: makeSharedPreferencesTime(context).also {
                instanceSP = it
            }
        }

        private fun makeSharedPreferencesTime(context: Context): SharedPreferencesTime {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesTime()
        }

    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(TIME, time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(TIME, 0)

}