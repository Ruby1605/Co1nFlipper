package com.example.co1nflipper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog

class resultado : AppCompatActivity() {

    lateinit var imagenHome: ImageView
    private lateinit var database: CoinFlipDatabase
    private lateinit var listView: ListView
    private lateinit var lista: ArrayAdapter<String>
    private lateinit var imgInformativo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        imagenHome = findViewById(R.id.botonResultado)
        imagenHome.setImageResource(R.drawable.home)

        imgInformativo = findViewById(R.id.imgInformativo)
        database = CoinFlipDatabase(this)
        listView = findViewById(R.id.listaResultados)

        //funcion para sacar la informacion de la imagen informativa
        imgInformativo.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Información")
            builder.setMessage("Mantén pulsado un resultado para eliminarlo")
            builder.setPositiveButton("Ok") { _, _ ->
                // No hacer nada si el usuario cancela
            }

            val dialog = builder.create()
            dialog.show()
        }

        // Función para eliminar un resultado al mantenerlo pulsado

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val valorSeleccionado = lista.getItem(position)

            if (valorSeleccionado != null) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmar eliminación")
            builder.setMessage("¿Estás seguro de que quieres eliminar este resultado?")
            builder.setPositiveButton("Sí") { _, _ ->
                // Borrar el resultado

                database.borrarResultadoPorValor(valorSeleccionado)

                lista.remove(valorSeleccionado)
                lista.notifyDataSetChanged()
            }
            builder.setNegativeButton("No") { _, _ ->
                // No hacer nada si el usuario cancela
            }

            val dialog = builder.create()
            dialog.show()
            }
            true
        }

        cargarResultado()
    }
    fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)

        // Agregar opciones al menú desplegable
        popupMenu.menu.add("Mapas")
        popupMenu.menu.add("Acerca de")
        popupMenu.menu.add("Opciones")


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
//funcion para Cargar los resultados resultados a la lista
    private fun cargarResultado(){
        val resultados = database.getResultados()
        val listaResultados = ArrayList<String>()

        while (resultados.moveToNext()) {
        listaResultados.add(resultados.getString(resultados.getColumnIndexOrThrow(CoinFlipDatabase.COLUMN_RESULT)))
        }

        lista = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaResultados)
        listView.adapter = lista
}
    }

