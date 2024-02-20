package com.example.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountries.databinding.RecyclerRowBinding
import com.example.kotlincountries.model.CountryModel
import com.example.kotlincountries.utils.downloadFromUrl
import com.example.kotlincountries.utils.placeholderProgressBar
import com.example.kotlincountries.view.CountryFeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<CountryModel>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(layoutInflater,parent,false)
        return CountryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        val url = countryList[position].flag
        val holderContext = holder.binding.root.context

        holder.binding.countryName.text = countryList[position].name
        holder.binding.capitalName.text = countryList[position].capital

        holder.binding.root.setOnClickListener {
            val action = CountryFeedFragmentDirections.actionCountryFeedFragmentToDetailsFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromUrl(url, placeholderProgressBar(holderContext))

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}