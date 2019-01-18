package com.example.namequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import java.util.jar.Attributes

class DatabaseActivity : AppCompatActivity() {

    private val a = 5
    private val b = 10
    private var lv: ListView? = null
    private var customeAdapter: NameAdapter? = null
    private var namesArrayList: ArrayList<Names>? = null
    private val imgIdList = intArrayOf(R.drawable.person1, R.drawable.person1, R.drawable.person1, R.drawable.person1, R.drawable.person1, R.drawable.person1, R.drawable.person1, R.drawable.person1)
    private val nameList = arrayOf("Benz", "Bike", "Car", "Carrera", "Ferrari", "Harly", "Lamborghini", "Silver")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.list_database) as ListView

        namesArrayList = populateList()
        Log.d("hjhjh", namesArrayList!!.size.toString() + "")
        customeAdapter = NameAdapter(this, namesArrayList!!)
        lv!!.adapter = customeAdapter



    }

    private fun populateList(): ArrayList<Names> {

        val list = ArrayList<Names>()

        for (i in 0..7) {
            val names = Names();
            names.setName(nameList[i])
            names.setImgId(imgIdList[i])
            list.add(names)
        }

        return list
    }
}
