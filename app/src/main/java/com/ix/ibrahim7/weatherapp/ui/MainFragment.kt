package com.ix.ibrahim7.weatherapp.ui

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
import com.ix.ibrahim7.weatherapp.databinding.FragmentMainBinding
import com.ix.ibrahim7.weatherapp.ui.viewmodel.HomeViewModel
import com.ix.ibrahim7.weatherapp.util.Constant.TAG


class MainFragment : Fragment() {


    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    lateinit var mBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            executePendingBindings()
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getWeather("London,uk")

        viewModel.dataWeatherLiveData.observe(viewLifecycleOwner, Observer {
            Log.v("$TAG weather",it.data.toString())
        })


    }


}