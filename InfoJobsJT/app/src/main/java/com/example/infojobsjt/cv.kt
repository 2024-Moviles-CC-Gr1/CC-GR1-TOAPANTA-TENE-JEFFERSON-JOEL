package com.example.infojobsjt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class cv : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv)

        val botonImageSearch = findViewById<ImageButton>(R.id.imageButton7)
        botonImageSearch.setOnClickListener {
            irActividad((MainActivity:: class.java))
        }

        val botonImageOffer = findViewById<ImageButton>(R.id.imageButton8)
        botonImageOffer.setOnClickListener {
            irActividad((activity_mis_ofertas:: class.java))
        }

        val botonImageCV = findViewById<ImageButton>(R.id.imageButton9)
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