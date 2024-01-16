package com.example.co1nflipper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.app.AlertDialog
import android.content.DialogInterface



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

        builder.setTitle("Acerca de")
            .setMessage("Esta es una aplicación hecha por Ruben Casado Ruiz,el objetivo de dicha " +
                    "aplicacion es hacer una acción muy sencilla de lanzar una moneda y que salga un " +
                    "valor (Cara o Cruz).")
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })

        val dialog = builder.create()
        dialog.show()
    }


}