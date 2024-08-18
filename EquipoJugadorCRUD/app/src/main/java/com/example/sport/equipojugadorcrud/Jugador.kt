package com.example.sport.equipojugadorcrud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbJugador
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol
import java.util.Date

class Jugador : AppCompatActivity() {

    var idItemSeleccionado = 0

    @SuppressLint("MissingFlattedID","MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador)

        val nombre = findViewById<EditText>(R.id.txt_nombre_jugador)
        nombre.requestFocus()

        val edad = findViewById<EditText>(R.id.txt_edad_jugador)
        val pais = findViewById<EditText>(R.id.txt_pais_jugador)
        val estatura = findViewById<EditText>(R.id.txt_estatura_jugador)
        val fechaNacimiento = findViewById<EditText>(R.id.txt_fecha_nacimiento_jugador)
        val lesion = findViewById<EditText>(R.id.txt_lesion_jugador)

        val btnInsertar = findViewById<Button>(R.id.btn_insert_jugador)
        btnInsertar.setOnClickListener {
            val jugador = DbJugador(
                null,
                nombre.text.toString(),
                edad.text.toString().toInt(),
                pais.text.toString(),
                estatura.text.toString().toDouble(),
                Date(),  // Use current date or convert from fechaNacimiento.text.toString()
                lesion.text.toString().equals("Sí", ignoreCase = true),
                this
            )
            val resultado = jugador.insertJugador()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
            irActividad(VerJugadores::class.java)
        }

        val idEquipo = EquipoFutbol.idEquipoSelected + 1
        pais.setText(idEquipo.toString()) // This seems odd; maybe update with a relevant field

        val idEquipoFutbol = EquipoFutbol.idEquipoSelected
        val equipoAux = DbEquipoFutbol(null, "", "", "", 0.0, 0, Date(), false, this)

        val textViewEquipo = findViewById<TextView>(R.id.tv_titleEquipo)
        textViewEquipo.text = equipoAux.getEquipoFutbolById(idEquipoFutbol)?.getNombre()
    }

    private fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.txt_nombre_jugador)
        nombre.setText("")

        val edad = findViewById<EditText>(R.id.txt_edad_jugador)
        edad.setText("")

        val pais = findViewById<EditText>(R.id.txt_pais_jugador)
        pais.setText("")

        val estatura = findViewById<EditText>(R.id.txt_estatura_jugador)
        estatura.setText("")

        val fechaNacimiento = findViewById<EditText>(R.id.txt_fecha_nacimiento_jugador)
        fechaNacimiento.setText("")

        val lesion = findViewById<EditText>(R.id.txt_lesion_jugador)
        lesion.setText("")

        nombre.requestFocus()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
