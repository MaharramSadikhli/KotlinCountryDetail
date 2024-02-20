package com.example.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountries.model.CountryDB
import com.example.kotlincountries.model.CountryModel
import com.example.kotlincountries.service.ServiceAPI
import com.example.kotlincountries.utils.SharedPreferencesTime
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CountryFeedViewModel(application: Application): RootViewModel(application) {

    private val serviceApi = ServiceAPI()
    private val disposable = CompositeDisposable()
    private var sharedPreferencesTime = SharedPreferencesTime(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<CountryModel>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = sharedPreferencesTime.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            // get data from sqlite
            getDataFromSQLite()
        } else {
            // get data from api
            getDataFromAPI()
        }




    }

    fun refreshFromAPI() {
        // get data from api
        getDataFromAPI()
    }

    private fun getDataFromAPI() {

        val service = serviceApi.getData()

        loading.value = true

        disposable
            .add(
                service
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object: DisposableSingleObserver<List<CountryModel>>() {
                        override fun onSuccess(t: List<CountryModel>) {
                            Toast.makeText(getApplication(), "from api", Toast.LENGTH_LONG).show()
                            // store sqlite
                            storeDataSQLite(t)
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            error.value = true
                            loading.value = false
                        }

                    })
            )

    }

    private fun getDataFromSQLite() {
        loading.value = true

        launch {
            val dao = CountryDB(getApplication()).countryDao()
            val getCountries = dao.getAllCountries()

            showData(getCountries)
            Toast.makeText(getApplication(), "from sqlite", Toast.LENGTH_LONG).show()
        }
    }

    private fun showData(countryList: List<CountryModel>) {
        countries.value = countryList
        error.value = false
        loading.value = false
    }

    private fun storeDataSQLite(countryList: List<CountryModel>) {
        launch {
            val dao = CountryDB(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*countryList.toTypedArray())

            var i = 0
            while (i < countryList.size) {
                countryList[i].uuid = listLong[i].toInt()
                i++
            }

            showData(countryList)
        }

        sharedPreferencesTime.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}