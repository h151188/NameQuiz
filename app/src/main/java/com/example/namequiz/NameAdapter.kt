package com.example.namequiz

import android.app.Activity
import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NameAdapter(private var context: Context, private var nameList: ArrayList<Names>) : BaseAdapter() {

    override fun getCount(): Int { return nameList.size }
    override fun getItem(i: Int): Any { return nameList[i] }
    override fun getItemId(i: Int): Long { return i.toLong() }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup) : View {
        var view = view
        if(view == null) {
            // inflate layout resource if its null
            view = LayoutInflater.from(context).inflate(R.layout.listview_database, viewGroup, false)
        }

        // get current name
        val name = this.getItem(i) as Names

        // reference textviews and imageviews from our layout
        val img = view!!.findViewById<ImageView>(R.id.imageId) as ImageView
        val nameText = view.findViewById<TextView>(R.id.personName) as TextView

        // Bind data to TextView and ImageView
        nameText.text = name.getName()
        img.setImageResource(name.getImgId())

        return view
    }

}