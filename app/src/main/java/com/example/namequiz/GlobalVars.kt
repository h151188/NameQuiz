package com.example.namequiz

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v4.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class GlobalVars : Application() {
    var names = ArrayList<Names>()

    override fun onCreate() {
        super.onCreate()
        names.addAll(initArray())
    }

    /**
     * Initialize an arraylist of Names-objects on startup
     */
    fun initArray(): ArrayList<Names> {
        var list = ArrayList<Names>()
        var name = Names("Espen", saveImageToInternalStorage(resources.getIdentifier("espen", "drawable", packageName)).toString())

        list.add(name)
        name = Names("Lars", saveImageToInternalStorage(resources.getIdentifier("lars", "drawable", packageName)).toString())

        list.add(name)
        name = Names("Glenn", saveImageToInternalStorage(resources.getIdentifier("glenn", "drawable", packageName)).toString())

        list.add(name)
        return list
    }

    /**
     * Saving image to internal storage from drawble resource:
     */
    private fun saveImageToInternalStorage(drawableId:Int):Uri{
        // Get the image from drawable resource as drawable object
        val drawable = ContextCompat.getDrawable(applicationContext,drawableId)

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Get the context wrapper instance
        val wrapper = ContextWrapper(applicationContext)

        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)

        // Create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }
}