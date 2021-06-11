package com.huyhuynh.mypokedex.test.readwriteimage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.utils.ReadWriteImage
import com.huyhuynh.mypokedex.screen.utils.ReadWriteImage.pictureImagePath
import com.huyhuynh.mypokedex.screen.utils.Utils
import java.io.File


class ReadWriteImageActivity : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var btnOpen: Button
    lateinit var btnCamera: Button
    lateinit var imgView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_write_image)
        init()
        checkAndRequestPermissions()
        control()
    }

    //Kiểm tra và xin quyền trên android 6.0 trở lên
    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(permission)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 199)
        }
    }


    private fun init() {
        btnSave = findViewById(R.id.btnSaveImage)
        btnOpen = findViewById(R.id.btnOpenImage)
        btnCamera = findViewById(R.id.btnCamera)
        imgView = findViewById(R.id.imgView)
    }

    private fun control() {
        btnOpen.setOnClickListener {
            ReadWriteImage.readImageFromGallery(this)
        }
        btnCamera.setOnClickListener {
            ReadWriteImage.readImageFromCameraFull(this)
        }
        btnSave.setOnClickListener {
            ReadWriteImage.saveImage(imgView, "text", this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ReadWriteImage.PICK_IMAGE -> {
                    Utils.log("onActivityResult", "RESULT_OK - PICK_IMAGE: ${data}")
                    val imageUri: Uri? = data?.data
                    imgView.setImageURI(imageUri)
                }
                ReadWriteImage.CAMERA_IMAGE -> {
                    Utils.log("onActivityResult", " - CAMERA_IMAGE: ${data}")
                    //imgView.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    val imgFile = File(pictureImagePath)
                    if (imgFile.exists()) {
                        val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                        imgView.setImageBitmap(myBitmap)
                    }
                }
            }
        }
    }
}