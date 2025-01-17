package com.ix.ibrahim7.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ix.ibrahim7.weatherapp.R
import com.ix.ibrahim7.weatherapp.databinding.ActivityMainBinding
import com.ix.ibrahim7.weatherapp.util.Constant.setUpStatusBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        setUpStatusBar(this,1)
        setSupportActionBar(toolbar)
        toolbar.visibility= View.GONE
    }
}