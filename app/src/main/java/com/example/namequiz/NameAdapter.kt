package com.example.namequiz

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class NameAdapter(private var context: Context, private var nameList: MutableList<Names>?) : BaseAdapter() {

    override fun getCount(): Int { return nameList!!.size }
    override fun getItem(i: Int): Any { return nameList!![i] }
    override fun getItemId(i: Int): Long { return i.toLong() }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            // inflate layout resource if its null
            view = LayoutInflater.from(context).inflate(R.layout.listview_database, viewGroup, false)
        }

        // get current name
        val name = this.getItem(i) as Names

        // reference textviews and imageviews from our layout
        val img = view!!.findViewById(R.id.imageId) as ImageView
        val nameText = view.findViewById(R.id.personName) as TextView
        val delButton = view.findViewById(R.id.button_database_delete) as Button

        delButton.setOnClickListener {
            nameList!!.remove(name)
            AppDatabase.getDatabase(context).namesDao()?.delete(name)

            notifyDataSetChanged();
        }

        // Bind data to TextView and ImageView
        nameText.text = name.name
        var imgFile = Uri.parse(name.imgId) as Uri

        img.setImageURI(imgFile)

        return view
    }
}