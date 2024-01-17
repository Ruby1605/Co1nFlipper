package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale


class Normal : AppCompatActivity() {
    //inicializamos las variables
    lateinit var btnLanzar:Button
    lateinit var imagenMoneda:ImageView
    lateinit var imagenHome:ImageView
    lateinit var txtResultado : TextView
    //inicializamos el contador y el total de ciclos
    var contador = 0
    val totalCiclos = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        btnLanzar = findViewById(R.id.lanzarNormal)
        imagenMoneda = findViewById(R.id.imgMonedaCruz)
        txtResultado = findViewById(R.id.txtResultadoNormal)
        imagenHome = findViewById(R.id.botonNormalAction)

        imagenHome.setImageResource(R.drawable.home)

        btnLanzar.setOnClickListener {
            lanzarMoneda()
        }
    }

    // Función para cambiar de actividad a la de "Main"
    fun btnNormalAction(view: View) {
        val btn = Intent(this, MainActivity::class.java)
        startActivity(btn)
}
    // Función para cambiar de actividad a la de "Combate"
    fun btnCombateAction(view: View) {
        val btn = Intent(this, combate::class.java)
        startActivity(btn)
    }
    // Función para cambiar de actividad a la de "Resultado"
    fun btnResultado(view: View) {
        val btn = Intent(this, resultado::class.java)
        startActivity(btn)
    }

    // Función para lanzar la moneda haciendo una "animación" y mostrando el resultado
    private fun lanzarMoneda() {
        txtResultado.text = ""

        if (contador < totalCiclos) {
            // Alternar entre las imágenes de cara y cruz
            if (contador % 2 == 0) {
                imagenMoneda.setImageResource(R.drawable.monedadecara)
            } else {
                imagenMoneda.setImageResource(R.drawable.monedacruz)
            }

            contador++
            btnLanzar.postDelayed({
                lanzarMoneda()
            }, 300)
        } else {
            val resultado = (1..2).random()
            val idiomaDispositivo = Locale.getDefault().language
            contador=0
            if (idiomaDispositivo == "en") {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "      It's face"
                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "      It's cross"
                }
            } else {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "      Ha salido cara"
                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "      Ha salido cruz"
                }
            }
        }
    }

}