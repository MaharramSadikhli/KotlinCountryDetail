package com.example.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountries.model.CountryModel

class CountryFeedViewModel: ViewModel() {

    val countries = MutableLiveData<List<CountryModel>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshData() {
        val country1 = CountryModel(
            "Turkey",
            "Ankara",
            "Asia",
            "TRY",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Flag_of_Turkey.svg/2560px-Flag_of_Turkey.svg.png",
            "Turkish"
        )

        val country2 = CountryModel(
            "Germany",
            "Berlin",
            "Europe",
            "EUR",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Flag_of_Germany.svg/2560px-Flag_of_Germany.svg.png",
            "German"
        )

        val country3 = CountryModel(
            "Azerbaijan",
            "Baku",
            "Asia",
            "AZN",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Flag_of_Azerbaijan.svg/2560px-Flag_of_Azerbaijan.svg.png",
            "Azerbaijani"
        )


        val countryList = arrayListOf<CountryModel>(country1, country2, country3)

        countries.value = countryList
        error.value = false
        loading.value = false
    }

}