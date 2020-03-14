package com.example.weather.mainActivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.ApiFactory
import com.example.weather.R
import com.example.weather.WeatherService
import com.example.weather.cityInfroActivity.InfoActivity
import com.example.weather.constants.Constants
import com.example.weather.response.WeatherResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.UnknownHostException

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    private var adapter: CityAdapter? = null
    private var service: WeatherService = ApiFactory.weatherService
    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(this)
    private var latitude: Double = Constants.COORDINATES.DEFAULT_LATITUDE
    private var longitude: Double = Constants.COORDINATES.DEFAULT_LONGITUDE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Constants.REQUESTS.REQUEST_CODE)
        setSearchListener()
    }

    private fun setSearchListener() {
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(s: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                launch {
                    val response = withContext(Dispatchers.IO) {
                        service.weatherByName(sv.query.toString())
                    }
                    if (response.isSuccessful) {
                        setIntentForNextActivity("city id", response.body())
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "U TEBYA BEDI S BASHKOY",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                return true
            }
        })
    }

    private fun setWeatherInNearestCity() {
        service = ApiFactory.weatherService
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.citiesInCicle(latitude, longitude, Constants.NEARCITY.CITY_COUNT)
                }
                if (response.isSuccessful) {
                    setAdapter(response.body()?.list)
                } else {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Cant find this city",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                }
            } catch (ex: UnknownHostException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "No internet connection",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }
    }

    private fun getGeoPisition() {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            latitude = it.latitude
            longitude = it.longitude
            setObserver()
        }
    }

    private fun setObserver() {
        try {
            val mainViewModel by lazy {
                ViewModelProviders.of(
                    this,
                    ModelFactory(latitude, longitude)
                ).get(MainViewModel::class.java)
            }
            mainViewModel.cityList.observe(this, Observer {
                setAdapter(it)
            })
        } catch (ex: UnknownHostException) {
            Snackbar.make(
                findViewById(android.R.id.content),
                "No internet connection",
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    private fun setIntentForNextActivity(name: String, value: WeatherResponse?) {
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra(name, value?.id)
        startActivity(intent)
    }

    private fun setAdapter(list: List<WeatherResponse>?) {
        adapter = list?.let {
            CityAdapter(it) {
                setIntentForNextActivity("city id", it)
            }
        }
        rv_nearestCity.adapter = adapter
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.REQUESTS.REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) getGeoPisition()
                else setWeatherInNearestCity()
        }
    }

    private fun checkPermissions(permission: String, requestCode: Int) {
        val permissionsStatus = this.let { ContextCompat.checkSelfPermission(it, permission) }
        if (permissionsStatus == PackageManager.PERMISSION_GRANTED) {
            getGeoPisition()
        } else {
            this.let { ActivityCompat.requestPermissions(it, arrayOf(permission), requestCode) }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

}

