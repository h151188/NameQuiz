package com.example.namequiz

class Names {

    private var name: String? = null
    private var imgId: Int = 0

    fun getName(): String {
        return name.toString()
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImgId(): Int {
        return imgId
    }

    fun setImgId(imgId: Int) {
        this.imgId = imgId
    }

}