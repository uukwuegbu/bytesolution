package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentCountryDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_detail, container, false)

        binding.buttonBack.setOnClickListener {
            goToCountriesList()
        }


        return binding.root
    }

    private fun goToCountriesList() {
        val action = CountryDetailFragmentDirections.actionCountryDetailFragmentToCountriesListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}