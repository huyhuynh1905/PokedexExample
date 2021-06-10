package com.huyhuynh.mypokedex.test

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.huyhuynh.mypokedex.R
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {
    lateinit var flp: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        flp = LocationServices.getFusedLocationProviderClient(this)
        btn_getlocation.setOnClickListener {
            Log.d("getLocation", "Click Button ================")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED){
                Log.d("getLocation", "Trueeee")
                getCurrentLocation()
            } else {
                Log.d("getLocation", "Falseeee")
                checkAndRequestPermissions()
            }

        }
        //=====================
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            0.toFloat(),
            locationListenerGps
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000L,
            0.toFloat(),
            locationListenerGps
        )
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )){
            flp.lastLocation.addOnCompleteListener { p0 ->
                val loc: Location = p0.result
                Log.d("getLocation", "Latitide: ${loc.latitude} - Longtitude: ${loc.longitude}")
            }
        }
    }
    var locationListenerGps: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            //use location as it is the latest value
            Log.d("getLocation", "onLocationChanged::Latitide: ${location?.latitude} - Longtitude: ${location?.longitude}")
        }

        override fun onProviderDisabled(provider: String?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    //Kiểm tra và xin quyền trên android 6.0 trở lên
    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==199&& grantResults.isNotEmpty() && (grantResults[0]+grantResults[1]) == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation()
        }
    }
}