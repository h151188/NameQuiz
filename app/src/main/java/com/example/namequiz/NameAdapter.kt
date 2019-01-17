package com.example.namequiz

import android.app.Activity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NameAdapter(private var activity: Activity, private var items: ArrayList<MainActivity.Names>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var imageId: TextView? = null;
        var personName: TextView? = null;

        init {
            this.imageId = row?.findViewById<TextView>(R.id.imageId);
            this.personName = row?.findViewById<TextView>(R.id.personName);
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) : View {
        var convertView = convertView;
        val viewHolder: ViewHolder;
        if(convertView==null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
            convertView = inflater.inflate(R.layout.listview_database, null, true)
            viewHolder = ViewHolder(convertView)
            convertView?.tag = viewHolder
        } else {
            convertView = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        var namesDb = items[position]
        viewHolder.imageId?.text = namesDb.imgId
        viewHolder.personName?.text = namesDb.name

        return convertView as View
    }

    override fun getItem(i: Int): MainActivity.Names {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}