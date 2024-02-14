package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.location.Geocoder
import java.util.Locale


class ResultadoMapas : AppCompatActivity() {
    private val ubicaciones = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_mapas)

        val sharedPreferences = getSharedPreferences("ubicaciones", MODE_PRIVATE)
        val latitud = sharedPreferences.getFloat("latitud", 0.0f)
        val longitud = sharedPreferences.getFloat("longitud", 0.0f)

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitud.toDouble(), longitud.toDouble(), 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val cityName = addresses[0].locality
            val countryName = addresses[0].countryName

            val ubicacionActual = "$cityName, $countryName"

            val ubicaciones = sharedPreferences.getStringSet("ubicacionesSet", mutableSetOf())
            ubicaciones?.add(ubicacionActual)

            sharedPreferences.edit().putStringSet("ubicacionesSet", ubicaciones).apply()

            val listView = findViewById<ListView>(R.id.listaResultados)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ubicaciones?.toList() ?: emptyList())
            listView.adapter = adapter
        }
    }
    // ...

    fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)

        // Agregar opciones al menú desplegable
        popupMenu.menu.add("Mapas")
        popupMenu.menu.add("Opciones")
        popupMenu.menu.add("Acerca de")



        // Manejar clics en las opciones del menú
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.title) {
                "Mapas" -> {
                    val btn = Intent(this, Mapas::class.java)
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

}