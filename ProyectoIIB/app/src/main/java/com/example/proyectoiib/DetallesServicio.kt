package com.example.proyectoiib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetallesServicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_servicio)

        val botonVolver = findViewById<Button>(R.id.button10)
        botonVolver.setOnClickListener {
            irActividad((HistorialServicios:: class.java))
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}