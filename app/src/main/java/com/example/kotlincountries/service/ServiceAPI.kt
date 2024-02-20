package com.example.kotlincountries.service

import com.example.kotlincountries.model.CountryModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ServiceAPI {

    private val builder = Retrofit.Builder()

    private val retrofit = builder
        .baseUrl(URL.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val service = retrofit.create(CountryAPI::class.java)

    fun getData(): Single<List<CountryModel>> {
        return service.getCountries()
    }


}