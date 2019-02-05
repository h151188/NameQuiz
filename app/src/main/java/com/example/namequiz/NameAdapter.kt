package com.example.namequiz

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class NameAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NameAdapter.NamesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var names = emptyList<Names>() // Cached copy of words

    inner class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val namesItemView: TextView = itemView.findViewById(R.id.textView)
        // reference textviews and imageviews from our layout
        val img = itemView.findViewById(R.id.imageId) as ImageView
        val nameText = itemView.findViewById(R.id.personName) as TextView
        val delButton = itemView.findViewById(R.id.button_database_delete) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NamesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val current = names[position]
        holder.nameText.text = current.name
        var imgFile = Uri.parse(current.imgId) as Uri

        holder.img.setImageURI(imgFile)
    }

    internal fun setNames(names: List<Names>) {
        this.names = names
        notifyDataSetChanged()
    }

    override fun getItemCount() = names.size
}

