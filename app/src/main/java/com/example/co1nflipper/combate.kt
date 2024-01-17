package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale

class combate : AppCompatActivity() {
    //inicializamos las variables
    lateinit var btnLanzar:Button
    lateinit var imagenMoneda:ImageView
    lateinit var imagenHome:ImageView
    lateinit var txtResultado : TextView
    lateinit var jugador1 : EditText
    lateinit var jugador2 : EditText
    //inicializamos el contador y el total de ciclos
    var contador = 0
    val totalCiclos = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combate)

        btnLanzar = findViewById(R.id.btnLanzarCombate)
        imagenMoneda = findViewById(R.id.imgMonedaCombate)
        txtResultado = findViewById(R.id.txtResultadoCombate)
        imagenHome = findViewById(R.id.btnCombateAction)
        jugador1 = findViewById(R.id.jugador1)
        jugador2 = findViewById(R.id.editTextText2)

        imagenHome.setImageResource(R.drawable.home)

        btnLanzar.setOnClickListener {
            lanzarMoneda()
        }
    }

    // Función para cambiar de actividad a la de "Main"
    fun btnCombateAction(view: View) {
        val btn = Intent(this, MainActivity::class.java)
        startActivity(btn)
    }
    // Función para cambiar de actividad a la de "Normal"
    fun btnNormalAction(view: View) {
        val btn = Intent(this, Normal::class.java)
        startActivity(btn)
    }
    // Función para cambiar de actividad a la de "Resultado"
    fun btnResultado(view: View) {
        val btn = Intent(this, resultado::class.java)
        startActivity(btn)
    }

    // Función para lanzar la moneda
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
            contador=0
            var jug1 = jugador1.text.toString()
            var jug2 = jugador2.text.toString()
            val idiomaDispositivo = Locale.getDefault().language
            val resultado = (1..2).random()

            if (idiomaDispositivo == "en") {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "              It's face\n" +
                            "   You have won $jug1!"
                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "            It's cross\n" +
                            "   You have won $jug2!"
                }
            } else {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "           Ha salido cara \n" +
                            "   ¡Has ganado $jug1!"
                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "           Ha salido cruz\n" +
                            "   ¡Has ganado $jug2!"
                }
            }
        }


    }

}