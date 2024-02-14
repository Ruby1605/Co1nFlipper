package com.example.co1nflipper

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.widget.Button

class Mapas : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var btnSaveUbi: Button
    private lateinit var btnUbis: Button


    companion object {
        private const val REQUEST_CODE_LOCATION = 0
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapas)

        btnUbis = findViewById(R.id.btnUbis)
        btnUbis.setOnClickListener {
            val btn = Intent(this, ResultadoMapas::class.java)
            startActivity(btn)
        }
        btnSaveUbi = findViewById(R.id.buttonSave)
        btnSaveUbi.setOnClickListener {
            val location = map.myLocation
            if (location != null) {
                val sharedPreferences = getSharedPreferences("ubicaciones", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putFloat("latitud", location.latitude.toFloat())
                editor.putFloat("longitud", location.longitude.toFloat())
                editor.commit()
            }
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val madridLocation = LatLng(40.416775, -3.703790)
        map.addMarker(MarkerOptions().position(madridLocation).title("Marcador del lanzamiento"))
        map.moveCamera(CameraUpdateFactory.newLatLng(madridLocation))

        enableLocation()

    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun enableLocation(){
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)

        // Agregar opciones al menú desplegable
        popupMenu.menu.add("Menu")
        popupMenu.menu.add("Opciones")
        popupMenu.menu.add("Acerca de")


        // Manejar clics en las opciones del menú
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.title) {
                "Menu" -> {
                    val btn = Intent(this, MainActivity::class.java)
                    startActivity(btn)
                    true
                }
                "Opciones" -> {
                    val btn = Intent(this, Opciones::class.java)
                    startActivity(btn)
                    true
                }
                "Acerca de" -> {
                    val btn = Intent(this, AcercaDe::class.java)
                    startActivity(btn)
                    true
                }

                else -> false
            }
        }

        // Mostrar el menú desplegable
        popupMenu.show()
    }


    /* Función para cambiar de actividad a la de "Normal"
     desde los botones de Inicio y del ActionBar*/

    fun btnNormalAction(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    /* Función para cambiar de actividad a la de "Combate"
     desde los botones de Inicio y del ActionBar*/

    fun btnCombateAction(view: View) {
        val btn = Intent(this, combate::class.java)
        startActivity(btn)
    }
    /* Función para cambiar de actividad a la de "Resultado"
     desde el boton del ActionBar*/
    fun btnResultado(view: View) {
        val btn = Intent(this, resultado::class.java)
        startActivity(btn)
    }

}