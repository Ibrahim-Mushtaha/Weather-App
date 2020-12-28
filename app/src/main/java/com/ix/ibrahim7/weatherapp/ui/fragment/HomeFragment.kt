package com.ix.ibrahim7.weatherapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ix.ibrahim7.weatherapp.R
import com.ix.ibrahim7.weatherapp.adapter.CityAdapter
import com.ix.ibrahim7.weatherapp.databinding.FragmentHomeBinding
import com.ix.ibrahim7.weatherapp.model.weather.Weather
import com.ix.ibrahim7.weatherapp.ui.viewmodel.HomeViewModel
import com.ix.ibrahim7.weatherapp.util.Constant
import com.ix.ibrahim7.weatherapp.util.Constant.DURATION
import com.ix.ibrahim7.weatherapp.util.Constant.convertLongToTime
import com.ix.ibrahim7.weatherapp.util.Constant.dialog
import com.ix.ibrahim7.weatherapp.util.Constant.setAnimation
import com.ix.ibrahim7.weatherapp.util.Constant.showDialog
import com.ix.ibrahim7.weatherapp.util.Resource
import java.io.IOException
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    lateinit var mBinding:FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    private val adapterSpinner by lazy {
        CityAdapter(ArrayList())
    }
    val temp="°"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            executePendingBindings()
        }
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.dataCityLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    mBinding.citySpinner.apply {
                        adapter = adapterSpinner
                        onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                                        viewModel.getWeather(adapterSpinner.content[p2].name.toString(),"metric")
                                    }

                                    override fun onNothingSelected(p0: AdapterView<*>?) {

                                    }
                                }
                    }
                    adapterSpinner.content.clear()
                    adapterSpinner.content.addAll(it.data!!)
                    adapterSpinner.notifyDataSetChanged()
                    Log.v("${Constant.TAG} city",it.data.toString())
                    dialog.dismiss()
                }

                is Resource.Error -> {
                    dialog.dismiss()
                    Log.e("eeee Error",it.message.toString())
                }

                is Resource.Loading -> {
                showDialog(requireActivity())
                }

            }
        })


        setAnimation(mBinding.cardView,0,DURATION)
        mBinding.tvImage.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.slide_up))

        viewModel.dataWeatherLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {weather->
                        initView(weather!!)
                    }
                    Log.v("${Constant.TAG} weather",it.data.toString())
                }
                is Resource.Error -> {
                    Log.e("eeee Error",it.message.toString())
                    Log.e("eeee Error",it.data.toString())
                }

                is Resource.Loading -> {
                }
            }
        })

    }


    private fun initView(weather: Weather){
        mBinding.txtHumidity.text= requireActivity().getString(R.string.temprature,weather!!.main!!.temp!!)+temp
        mBinding.txtMain.text=weather.weather!![0].main!!
        mBinding.txtCountry.text=weather.name
        mBinding.txtMax.text= requireActivity().getString(R.string.temprature,weather.main!!.tempMax)+temp
        mBinding.txtMin.text= requireActivity().getString(R.string.temprature,weather.main.tempMin)+temp
        mBinding.txtSunrise.text= weather.sys!!.sunrise!!.toString()
        mBinding.txtWind.text=weather.wind!!.speed!!.toString()
        mBinding.txtSunset.text= weather.sys.sunset!!.toString()
        mBinding.txtDate.text= weather.sys.sunset.toString()
        mBinding.txtDate.text=convertLongToTime(weather.dt!!)
    }


}