package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincountries.R
import com.example.kotlincountries.databinding.FragmentDetailsBinding
import com.example.kotlincountries.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid = DetailsFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            country ->

            country?.let {
                binding.countryNameDetails.text = country.name
                binding.countryCapitalDetails.text = country.capital
                binding.countryRegionDetails.text = country.region
                binding.countryCurrencyDetails.text = country.currency
                binding.countryLanguageDetails.text = country.language
            }
        })

    }

}