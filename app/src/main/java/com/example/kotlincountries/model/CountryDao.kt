package com.example.kotlincountries.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDao {

    @Query("SELECT * FROM countrymodel")
    suspend fun getAllCountries() : List<CountryModel>

    @Query("SELECT * FROM countrymodel WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : CountryModel

    @Query("DELETE FROM countrymodel")
    suspend fun deleteAllCountries()

    @Insert
    suspend fun insertAll(vararg countries: CountryModel) : List<Long>

}