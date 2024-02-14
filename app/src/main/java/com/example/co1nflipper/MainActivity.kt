package com.example.co1nflipper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.app.AlertDialog
import android.content.DialogInterface
import java.util.Locale
import android.widget.PopupMenu
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.app.PendingIntent
import android.app.AlarmManager
import java.util.Calendar
import com.example.co1nflipper.AlarmNotification.Companion.NOTIFICATION_ID
import android.os.SystemClock

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDayNight()
        setContentView(R.layout.activity_main)
        val acercaDe = findViewById<TextView>(R.id.AcercaDe)
        acercaDe.setOnClickListener {
            mostrarDialogoAcercaDe()
        }


    }

// Función para cambiar el tema de la aplicación
    fun setDayNight() {
        val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val theme = sharedPreferences.getBoolean("modoNocturno", false)
        if (theme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            delegate.applyDayNight()
            sharedPreferences.edit().putBoolean("modoNocturno", true).apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            delegate.applyDayNight()
            sharedPreferences.edit().putBoolean("modoNocturno", false).apply()
        }
    }

    //Menu desplegable  del ActionBar

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


    /* Función para cambiar de actividad a la de "Normal"
     desde los botones de Inicio y del ActionBar*/
   fun btnNormalInicio(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    fun btnNormalAction(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    /* Función para cambiar de actividad a la de "Combate"
     desde los botones de Inicio y del ActionBar*/
    fun btnCombateInicio(view: View) {
        val btn = Intent(this, combate::class.java)
        startActivity(btn)
    }
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

    // Función para mostrar el diálogo de Acerca De
    private fun mostrarDialogoAcercaDe() {
        val builder = AlertDialog.Builder(this)
        val idiomaDispositivo = Locale.getDefault().language
        if (idiomaDispositivo == "en"){
            builder.setTitle("About")
                .setMessage("This is an application made by Ruben Casado Ruiz, the objective of this" +
                        "application is to do a very simple action of flipping a coin and getting a " +
                        "value (Face or Cross). You can choose between two game modes, normal mode and combat mode," +
                        "the normal mode being an emulation of flipping the coin and in combat mode you can " +
                        "introduce two players. More information in the options section.")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })

            val dialog = builder.create()
            dialog.show()

        }else{
            builder.setTitle("Acerca de")
            .setMessage("Esta es una aplicación esta hecha por Ruben Casado Ruiz,el objetivo de dicha " +
                    "aplicacion es hacer una acción muy sencilla de lanzar una moneda y que salga un " +
                    "valor (Cara o Cruz). Podras elegir entre dos modos de juego, el modo normal y el modo combate," +
                    "siendo el modo normal una emulacion de lanzar la moneda al aire y elen modo combate podras " +
                    "introducir dos jugadores. Mas informacion en la seccion de opciones.")
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })

            val dialog = builder.create()
            dialog.show()
        }
    }
}