package com.example.proyectoiib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.button)
        botonLogin.setOnClickListener {
            irActividad((Login:: class.java))
        }
        val botonRegistrer = findViewById<Button>(R.id.button2)
        botonRegistrer.setOnClickListener {
            irActividad((Registro:: class.java))
        }

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}