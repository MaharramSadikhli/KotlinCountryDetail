package com.example.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountries.model.CountryDB
import com.example.kotlincountries.model.CountryModel
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : RootViewModel(application) {

    val countryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom(uuid: Int) {

        launch {
            val dao = CountryDB(getApplication()).countryDao()

            val getCountry = dao.getCountry(uuid)

            countryLiveData.value = getCountry
        }

    }

}