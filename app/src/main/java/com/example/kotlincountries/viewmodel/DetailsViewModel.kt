package com.example.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountries.model.CountryModel

class DetailsViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom() {
        val country = CountryModel(
            "Turkey",
            "Asia",
            "Ankara",
            "TRY",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Flag_of_Turkey.svg/2560px-Flag_of_Turkey.svg.png",
            "Turkish"
        )
        countryLiveData.value = country
    }

}