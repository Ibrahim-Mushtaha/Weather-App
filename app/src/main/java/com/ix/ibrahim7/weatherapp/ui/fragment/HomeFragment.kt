package com.ix.ibrahim7.weatherapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ix.ibrahim7.weatherapp.R
import com.ix.ibrahim7.weatherapp.databinding.FragmentHomeBinding
import com.ix.ibrahim7.weatherapp.ui.viewmodel.HomeViewModel
import com.ix.ibrahim7.weatherapp.util.Constant
import com.ix.ibrahim7.weatherapp.util.Resource


class HomeFragment : Fragment() {

    lateinit var mBinding:FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

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

        viewModel.getWeather("London,uk","metric")

        viewModel.dataWeatherLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data.let {weather->

                        mBinding.txtHumidity.text=weather!!.main!!.humidity!!.toString()
                        mBinding.txtMain.text= weather.weather!![0].main.toString()
                        mBinding.txtMax.text= weather.main!!.tempMax.toString()
                        mBinding.txtMin.text= weather.main.tempMin.toString()

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

}