package com.example.namequiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import android.R.attr.data
import android.content.ContextWrapper
import android.database.Cursor
import java.io.*


class AddActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_TAKE_PHOTO = 1
    val GET_FROM_GALLERY = 3
    var mCurrentPhotoPath: String = ""
    private lateinit var namesViewModel: NamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Get reference to buttons
        var btn_upload = findViewById(R.id.new_upload) as Button
        var btn_take_pic = findViewById(R.id.new_take_pic) as Button
        var btn_add = findViewById(R.id.new_add) as Button

        // Set on-click listener
        btn_take_pic.setOnClickListener {
            dispatchTakePictureIntent()
        }

        btn_add.setOnClickListener {
            addNewName()
        }

        btn_upload.setOnClickListener {
            uploadPicture()
        }
    }

    /**
     * Uploads a file from local gallery
     */
    fun uploadPicture() {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
            ), GET_FROM_GALLERY
        )
    }

    /**
     * Adds a new name to the global arraylist of names
     */
    fun addNewName() {
        var btn_name = findViewById(R.id.new_name) as EditText
        val replyIntent = Intent()
        var name: Names = Names(btn_name.text.toString(), mCurrentPhotoPath)
        //val gv = applicationContext as GlobalVars
        //gv.names.add(name)
        namesViewModel.insert(name)
        setResult(-1, replyIntent);
        finish()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    /**
     * Function for setting the picture taken to imageview and adding it to the gallery
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // If picture was taken successfully
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic()
            galleryAddPic()
        }

        // If picture was uploaded from local successfully
        if (requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK) {
            manageImageFromUri(data!!.getData())
        }
    }

    /**
     * Creates a file where the picture taken will be stored to
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }

    /**
     * Submits the picture to the gallery
     */
    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(mCurrentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
            System.out.println("Add to gallery " + mCurrentPhotoPath)
        }
    }

    /**
     * Decode a scaled image
     */
    private fun setPic() {
        var pic = findViewById(R.id.add_thumbnail) as ImageView
        // Get the dimensions of the View
        val targetW: Int = pic.width
        val targetH: Int = pic.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(mCurrentPhotoPath, this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            //inPurgeable = true
        }
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)?.also { bitmap ->
            pic.setImageBitmap(bitmap)
        }
    }

    /**
     * Saves the picture from gallery to application
     */
    private fun manageImageFromUri(uri: Uri) {
        var pic = findViewById(R.id.add_thumbnail) as ImageView
        var bitmap: Bitmap? = null

        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                this.getContentResolver(), uri
            )

        } catch (e: Exception) {
            // Manage exception ...
        }

        if (bitmap != null) {
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

            // saves the saved image path
            mCurrentPhotoPath = file.absolutePath
            pic.setImageBitmap(bitmap)
        }
    }
}
