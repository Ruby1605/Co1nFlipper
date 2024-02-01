package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import android.content.Context

class Opciones : AppCompatActivity() {
    private lateinit var swtchOscuro : Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        swtchOscuro = findViewById(R.id.swtchOscuro)

        swtchOscuro.setOnCheckedChangeListener { buttonView, isChecked ->
            val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("modoNocturno", isChecked).apply()
            if (isChecked) {
                swtchOscuro.isChecked = true
                cambiarOscuro()
            } else {
                swtchOscuro.isChecked = false
                cambiarClaro()
            }
        }

    }



    private fun cambiarOscuro() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun cambiarClaro() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
    fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)

        // Agregar opciones al menú desplegable
        popupMenu.menu.add("Menú")
        popupMenu.menu.add("Mapas")
        popupMenu.menu.add("Acerca de")



        // Manejar clics en las opciones del menú
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.title) {
                "Menú" -> {
                    val btn = Intent(this, MainActivity::class.java)
                    startActivity(btn)
                    true
                }
                "Mapas" -> {
                    //val btn = Intent(this, Acercade::class.java)
                    //startActivity(btn)
                    true
                }
                "Acerca de" -> {
                    //val btn = Intent(this, Acercade::class.java)
                    //startActivity(btn)
                    true
                }

                else -> false
            }
        }

        // Mostrar el menú desplegable
        popupMenu.show()
    }
    fun btnNormalAction(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    fun btnCombateAction(view: View) {
        val btn = Intent(this, combate::class.java)
        startActivity(btn)
    }
    fun btnResultado(view: View) {
        val btn = Intent(this, resultado::class.java)
        startActivity(btn)
    }
}