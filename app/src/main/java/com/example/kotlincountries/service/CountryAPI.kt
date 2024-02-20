package com.example.kotlincountries.service

import com.example.kotlincountries.model.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {


    @GET(URL.PATH_URL)
    fun getCountries(): Single<List<CountryModel>>

}