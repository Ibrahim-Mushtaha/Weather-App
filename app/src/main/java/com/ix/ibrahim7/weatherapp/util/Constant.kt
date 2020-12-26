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

object Constant {

    const val API_KEY="80a8125b9205c11d51a76afc20e5a47b"
    const val TAG = "eee"
    var on_attach = true
    var DURATION: Long = 350

    fun getSharePref(context: Context) =
            context.getSharedPreferences("Share", Activity.MODE_PRIVATE)

    fun editor(context: Context) = getSharePref(context).edit()



    lateinit var dialog: Dialog
    fun showDialog(activity: Activity) {
        dialog = Dialog(activity).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(true)
        }
        dialog.show()
    }



     fun setAnimation(itemView: View, i: Int,on_attach:Boolean,DURATION:Long) {
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
            window.statusBarColor = ContextCompat.getColor(activity, R.color.purple_700)
        } else {
            window.statusBarColor = ContextCompat.getColor(activity, R.color.purple_200)
        }
    }

}