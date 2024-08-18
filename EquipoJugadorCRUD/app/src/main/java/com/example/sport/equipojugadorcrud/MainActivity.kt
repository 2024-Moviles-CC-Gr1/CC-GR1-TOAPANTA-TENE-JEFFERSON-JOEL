package com.example.sport.equipojugadorcrud

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sport.deporteatletacrud.databinding.ActivityMainBinding
import com.example.sport.equipojugadorcrud.db.DbHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database
        val dbHelper: DbHelper = DbHelper(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        if (db != null) {
            // Database successfully created or opened
        } else {
            Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_LONG).show()
        }

        // Navigate to the EquipoFutbol activity
        irActividad(EquipoFutbol::class.java)
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
