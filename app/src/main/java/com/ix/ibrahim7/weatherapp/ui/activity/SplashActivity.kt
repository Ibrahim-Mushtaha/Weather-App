package com.ix.ibrahim7.weatherapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ix.ibrahim7.weatherapp.R
import com.ix.ibrahim7.weatherapp.databinding.ActivitySplashBinding
import com.ix.ibrahim7.weatherapp.ui.viewmodel.SplashState
import com.ix.ibrahim7.weatherapp.ui.viewmodel.SplashViewModel
import com.ix.ibrahim7.weatherapp.util.Constant.setUpStatusBar
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    lateinit var mBinding: ActivitySplashBinding

    val viewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpStatusBar(this, 1)

        viewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })



        val a: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        a.reset()

        imgSplashLogo.clearAnimation()
        imgSplashLogo.startAnimation(a)

    }

    private fun goToMainActivity() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        finish()
    }

}