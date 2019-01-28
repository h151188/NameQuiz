package com.example.namequiz

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
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
        var imgFile = Uri.parse(name.getImgId()) as Uri

        img.setImageURI(imgFile)

        return view
    }
/*
    private fun setPic(img: ImageView, name: Names) {
        // Get the dimensions of the View
        val targetW: Int = img.width
        val targetH: Int = img.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(name.getImgId(), this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(name.getImgId(), bmOptions)?.also { bitmap ->
            img.setImageBitmap(bitmap)
        }
    }
*/
}