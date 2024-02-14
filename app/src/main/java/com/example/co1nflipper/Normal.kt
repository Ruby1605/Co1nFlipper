package com.example.co1nflipper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale
import android.app.AlarmManager
import android.app.PendingIntent
import com.example.co1nflipper.AlarmNotification.Companion.NOTIFICATION_ID
import android.os.SystemClock
import android.net.Uri
import android.provider.Settings
import android.widget.PopupMenu


class Normal : AppCompatActivity() {
    companion object{
        const val MY_CHANNEL_ID = "MySuperChannel"
    }
    //inicializamos las variables
    private lateinit var database: CoinFlipDatabase
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

        database = CoinFlipDatabase(this)


        btnLanzar = findViewById(R.id.lanzarNormal)
        imagenMoneda = findViewById(R.id.imgMonedaCruz)
        txtResultado = findViewById(R.id.txtResultadoNormal)
        imagenHome = findViewById(R.id.botonNormalAction)

        imagenHome.setImageResource(R.drawable.home)

        createChannel()

        btnLanzar.setOnClickListener {
            lanzarMoneda()
            val sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
            val notificacionesActivas = sharedPreferences.getBoolean("notificacionesActivas", true)
            if (notificacionesActivas) {
                scheduleNotification()
            }
        }
    }
    //Menu desplegable  del ActionBar

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

    //Funcion para programar la notificacion
    private fun scheduleNotification() {
        val intent = Intent(applicationContext, AlarmNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Obtén la hora actual mas 5 minutos
        val triggerTime = SystemClock.elapsedRealtime() + 300000

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    // Programa la alarma para que se active en 5 minutos
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent)
                } else {
                    // Solicita el permiso SCHEDULE_EXACT_ALARM
                    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                        data = Uri.fromParts("package", packageName, null)
                    }
                    startActivity(intent)
                }
            } else {
                // Programa la alarma para que se active en 5 minutos
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent)
            }
        } else {
            // Para versiones de Android anteriores a Marshmallow, usa setExact
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent)
        }
    }

    //Funcion para crear el canal de notificaciones
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificaciones de la aplicación"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    // Función para lanzar la moneda haciendo una "animación" y mostrando el resultado (varios idiomas)
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

            //elegir el resultado que va a salir teniendo en cuenta el idioma del dispositivo
            val resultado = (1..2).random()
            val idiomaDispositivo = Locale.getDefault().language
            var resultadoBase = ""
            contador=0

            if (idiomaDispositivo == "en") {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "      It's face"
                    resultadoBase = "Normal(En)\nFace"

                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "      It's cross"
                    resultadoBase = "Normal(En)\nCross"
                }
            } else {
                if (resultado == 1) {
                    imagenMoneda.setImageResource(R.drawable.monedadecara)
                    txtResultado.text = "      Ha salido cara"
                    resultadoBase = "Normal\nCara"
                } else {
                    imagenMoneda.setImageResource(R.drawable.monedacruz)
                    txtResultado.text = "      Ha salido cruz"
                    resultadoBase = "Normal\nCruz"
                }
            }
            database.insertarResultados(resultadoBase)

        }
    }





}