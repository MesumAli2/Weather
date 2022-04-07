package com.mesum.weather

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mesum.weather.databinding.FragmentWeatherBinding
import com.mesum.weather.model.WeatherModel


class WeatherFragment : Fragment() {

    private var _binding : FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel  : WeatherViewModel by activityViewModels()
    private val weatherLocation : MutableList<WeatherModel> = mutableListOf()
    lateinit var recyclerView : RecyclerView
    val adapter = WeatherAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    var state = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchWeather()
        recyclerView = binding.weatherRecyclerView
        viewModel.watherData.observe(viewLifecycleOwner){
            weatherLocation.add(it)
            adapter.submitList(weatherLocation)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        }


    }

    private fun searchWeather() {
        binding.serachIcon.bringToFront()

        binding.serachIcon.setOnClickListener {
            if (state){
                binding.addWeather.visibility = View.VISIBLE
                addWeather()
                binding.addWeather.bringToFront()
                state = false
            }else {
                binding.addWeather.visibility = View.INVISIBLE
                state = true
            }

        }
    }

    private fun addWeather() {
        binding.addWeather.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE){
                    Toast.makeText(activity, "Weather Added ", Toast.LENGTH_LONG).show()
                    viewModel.getWeather(binding.addWeather.text.toString())
                    val keyboard = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.hideSoftInputFromWindow(binding.addWeather.windowToken, 0)
                    recyclerView.layoutManager?.scrollToPosition(1)

                }
                return true
            }
        })
    }






}