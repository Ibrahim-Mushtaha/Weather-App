package com.ix.ibrahim7.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ix.ibrahim7.weatherapp.R
import com.ix.ibrahim7.weatherapp.model.city.CityItem
import kotlinx.android.synthetic.main.item_design_spinner.view.*


class CityAdapter(val content: ArrayList<CityItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return content.size
    }

    override fun getItem(p0: Int): CityItem {
        return content[p0]
    }

    override fun getItemId(p0: Int): Long {
        return content[p0].id!!.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(p2!!.context)
            .inflate(R.layout.item_design_spinner, p2, false)

        view.txtTitleSpenner.text = content[p0].name

        return view
    }
}