package com.example.namequiz

import android.app.Application
import android.R
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import java.io.File
import java.io.FileOutputStream


class GlobalVars : Application() {
    var names = ArrayList<Names>()

    override fun onCreate() {
        super.onCreate()
        names.addAll(initArray())
        initPics()
    }

    /**
     * Initialize an arraylist of Names-objects on startup
     */
    fun initArray(): ArrayList<Names> {
        var list = ArrayList<Names>()
        var name = Names("Espen", "path")
        list.add(name)
        name = Names("Lars", R.drawable.lars)
        list.add(name)
        name = Names("Glenn", R.drawable.glenn)
        list.add(name)
        return list
    }

    /**
     * Saving image to sdcard from drawble resource:
     */
    fun initPics()  {
        // The path to SD Card
        val extStorageDirectory :String = Environment.getExternalStorageDirectory().toString()
        System.out.println("START " + extStorageDirectory)
        // Get a Bitmap object of the picture in drawable
        val bm = BitmapFactory.decodeResource(resources, R.drawable.espen) as Bitmap

        // Save picture to SD-card
        val file = File(extStorageDirectory, "espen.png") as File
        var outStream = FileOutputStream(file)
        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()
    }
}