package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu

class AcercaDe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de)
    }

    //Menu desplegable  del ActionBar

    fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)

        // Agregar opciones al menú desplegable
        popupMenu.menu.add("Mapas")
        popupMenu.menu.add("Opciones")
        popupMenu.menu.add("Menu")


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
                "Menu" -> {
                    val btn = Intent(this, MainActivity::class.java)
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