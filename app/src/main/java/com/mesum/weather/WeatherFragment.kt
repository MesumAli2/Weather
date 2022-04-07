package com.mesum.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import coil.load
import com.mesum.weather.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {

    private var _binding : FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel  : WeatherViewModel by activityViewModels()

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

        viewModel.watherData.observe(viewLifecycleOwner){
            binding.city.text = it.location.region.toString()
            binding.temp.text = "  ${it.current.temperature.toString()} °C"
            binding.tempFeellike.text = "  ${it.current.feelslike.toString()} °C"
            binding.uvIndex.text = it.current.uv_index.toString()
            binding.iconImage.load(it.current.weather_icons[0])



        }

    }


}