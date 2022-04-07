package com.mesum.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mesum.weather.databinding.FragmentWeatherBinding
import com.mesum.weather.model.WeatherModel


class WeatherFragment : Fragment() {

    private var _binding : FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel  : WeatherViewModel by activityViewModels()
    private val weatherLocation : MutableList<WeatherModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.weatherRecyclerView
        val adapter = WeatherAdapter()

        viewModel.watherData.observe(viewLifecycleOwner){
            weatherLocation.add(it)
            adapter.submitList(weatherLocation)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        }


    }


}