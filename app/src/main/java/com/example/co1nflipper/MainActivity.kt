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


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewAcercaDe: TextView = findViewById(R.id.AcercaDe)

        textViewAcercaDe.setOnClickListener {
            mostrarDialogoAcercaDe()
        }


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
                        "introduce two players.")
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
                    "introducir dos jugadores.")
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })

            val dialog = builder.create()
            dialog.show()
        }
    }
}