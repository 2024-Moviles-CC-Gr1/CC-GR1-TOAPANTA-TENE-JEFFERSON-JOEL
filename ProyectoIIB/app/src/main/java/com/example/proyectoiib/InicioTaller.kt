package com.example.proyectoiib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioTaller : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_taller)

        val botonRegistrarVehiculo = findViewById<Button>(R.id.button4)
        botonRegistrarVehiculo.setOnClickListener {
            irActividad((RegistrarVehiculo:: class.java))
        }
        val botonHistorialServicios = findViewById<Button>(R.id.button6)
        botonHistorialServicios.setOnClickListener {
            irActividad((HistorialServicios:: class.java))
        }
        val botonHistorialFacturas = findViewById<Button>(R.id.button7)
        botonHistorialFacturas.setOnClickListener {
            irActividad((HistorialFacturas:: class.java))
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}