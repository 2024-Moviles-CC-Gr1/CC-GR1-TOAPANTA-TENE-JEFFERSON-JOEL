package com.example.infojobsjt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class activity_mis_ofertas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_ofertas)

        val botonImageSearch = findViewById<ImageButton>(R.id.imageButton)
        botonImageSearch.setOnClickListener {
            irActividad((MainActivity:: class.java))
        }

        val botonImageOffer = findViewById<ImageButton>(R.id.imageButton3)
        botonImageOffer.setOnClickListener {
            irActividad((activity_mis_ofertas:: class.java))
        }

        val botonImageCV = findViewById<ImageButton>(R.id.imageButton4)
        botonImageCV.setOnClickListener {
            irActividad((cv:: class.java))
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}