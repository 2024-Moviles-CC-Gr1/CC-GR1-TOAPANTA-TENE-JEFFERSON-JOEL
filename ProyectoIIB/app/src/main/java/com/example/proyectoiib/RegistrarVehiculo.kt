package com.example.proyectoiib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistrarVehiculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_vehiculo)

        val botonRegistrarVehiculo = findViewById<Button>(R.id.button8)
        botonRegistrarVehiculo.setOnClickListener {
            irActividad((InicioTaller:: class.java))
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}