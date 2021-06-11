package com.huyhuynh.mypokedex.screen.utils

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


object ReadWriteImage {
    const val PICK_IMAGE = 11
    const val CAMERA_IMAGE = 12
    var pictureImagePath = ""

    fun readImageFromGallery(activity: AppCompatActivity){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    fun readImageFromCamera(activity: AppCompatActivity){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity.startActivityForResult(intent, CAMERA_IMAGE)
    }

    fun readImageFromCameraFull(activity: AppCompatActivity){
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "$timeStamp.png"
        val storageDir: File = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        pictureImagePath = storageDir.getAbsolutePath().toString() + "/" + imageFileName
        val file = File(pictureImagePath)
        val outputFileUri = Uri.fromFile(file)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        activity.startActivityForResult(cameraIntent, CAMERA_IMAGE)
    }

    fun saveImage(imageView: ImageView, title:String, context: Context): Uri {
        // Get the image from drawable resource as drawable object
        //val drawable = ContextCompat.getDrawable(context,drawable)

        // Get the bitmap from drawable object
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap

        // Save image to gallery
        val savedImageURL = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            bitmap,
            title,
            "Image of $title"
        )

        // Parse the gallery image url to uri
        return Uri.parse(savedImageURL)
    }
}