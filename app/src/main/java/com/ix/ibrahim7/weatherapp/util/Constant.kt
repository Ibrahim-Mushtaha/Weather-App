package com.ix.ibrahim7.weatherapp.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.ix.ibrahim7.weatherapp.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object Constant {

    const val API_KEY="80a8125b9205c11d51a76afc20e5a47b"
    const val TAG = "eee"
    var on_attach = true
    var DURATION: Long = 2000
    var FILENAMAE:String = "citylist.json"


    lateinit var dialog: Dialog
    fun showDialog(activity: Activity) {
        dialog = Dialog(activity).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(true)
        }
        dialog.show()
    }


    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(date)
    }


    fun getJsonDataFromAsset(context: Context,fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

     fun setAnimation(itemView: View, i: Int,DURATION:Long) {
        var i = i
        if (!on_attach) {
            i = -1
        }
        val isNotFirstItem = i == -1
        i++
        itemView.alpha = 0f
        val animatorSet = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)
        ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
        animator.startDelay = if (isNotFirstItem) DURATION / 2 else i * DURATION / 3
        animator.duration = 500
        animatorSet.play(animator)
        animator.start()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setUpStatusBar(activity: Activity, types: Int) {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (types == 1) {
            window.statusBarColor = ContextCompat.getColor(activity, R.color.background_color)
        } else {
            window.statusBarColor = ContextCompat.getColor(activity, R.color.purple_200)
        }
    }

}