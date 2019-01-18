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

class NameAdapter(private val context: Context, private val items: ArrayList<Names>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var personName: TextView? = null;
        internal var imageId: ImageView? = null;

        init {
            this.imageId = row?.findViewById<ImageView>(R.id.imageId);
            this.personName = row?.findViewById<TextView>(R.id.personName);
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) : View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.listview_database, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.personName.text = notesList[position].title
        vh.imageId.text = notesList[position].content

        return view
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}