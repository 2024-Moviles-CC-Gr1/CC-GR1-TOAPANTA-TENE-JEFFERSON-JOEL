package com.example.proyectoiib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HistorialFacturas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_facturas)

        val botonVolver = findViewById<Button>(R.id.button11)
        botonVolver.setOnClickListener {
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