package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class resultado : AppCompatActivity() {

    lateinit var imagenHome: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        imagenHome = findViewById(R.id.botonResultado)

        imagenHome.setImageResource(R.drawable.home)
    }
    // Función para cambiar de actividad a la de "Main"
    fun btnResultado(view: View) {
        val btn = Intent(this, MainActivity::class.java)
        startActivity(btn)
    }
    // Función para cambiar de actividad a la de "Normal"
    fun btnNormalAction(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    // Función para cambiar de actividad a la de "Combate"
    fun btnCombateAction(view: View) {
        val btn = Intent(this, combate::class.java)
        startActivity(btn)
    }

}