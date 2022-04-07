package com.mesum.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mesum.weather.databinding.WeatherListItemBinding
import com.mesum.weather.model.WeatherModel

class WeatherAdapter : ListAdapter<WeatherModel,  WeatherAdapter.WeatherViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem

        }
        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem.current == newItem.current
        }
    }

    class WeatherViewHolder(private var binding: WeatherListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(it: WeatherModel){
            binding.city.text = it.location.region.toString()
            binding.temp.text = "  ${it.current.temperature.toString()} °C"
            binding.tempFeellike.text = "  ${it.current.feelslike.toString()} °C"
            binding.uvIndex.text = it.current.uv_index.toString()
            binding.iconImage.load(it.current.weather_icons[0]){


                transformations(RoundedCornersTransformation(23f))
            }

            binding.hummidity.text = "${it.current.humidity.toString()}%"
            binding.weatherDescription.text = it.current.weather_descriptions[0].toString()
            binding.windDirection.text = " ${it.current.wind_dir}"
            binding.windSpeed.text = " ${it.current.wind_speed} " +
                    ""
            binding.windDegree.text = "${it.current.wind_degree}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return WeatherViewHolder(WeatherListItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}