package com.example.sport.equipojugadorcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.sport.equipojugadorcrud.db.DbJugador
import android.widget.Button
import android.widget.TextView
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol
import java.util.Date

class ActualizarJugador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_jugador)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idJugador = VerJugadores.idJugadorSelected

        var jugador = DbJugador(null, "", 0, "", 0.0, Date(), false, this)
        jugador = jugador.getJugadorById(idJugador) ?: return

        val id = findViewById<EditText>(R.id.update_id_jugador)
        id.setText(jugador.getidJugador().toString())

        val nombre = findViewById<EditText>(R.id.update_nombre_jugador)
        nombre.setText(jugador.getNombre())

        val edad = findViewById<EditText>(R.id.update_edad_jugador)
        edad.setText(jugador.getEdad().toString())

        val pais = findViewById<EditText>(R.id.update_pais_jugador)
        pais.setText(jugador.getPais())

        val estatura = findViewById<EditText>(R.id.update_estatura_jugador)
        estatura.setText(jugador.getEstatura().toString())

        val fechaNacimiento = findViewById<EditText>(R.id.update_fecha_nacimiento_jugador)
        fechaNacimiento.setText(jugador.getFechaNacimiento().toString())

        val lesion = findViewById<EditText>(R.id.update_lesion_jugador)
        lesion.setText(if (jugador.getLesion()) "Sí" else "No")

        val btnActualizarJugador = findViewById<Button>(R.id.btn_upd_jugador)
        btnActualizarJugador.setOnClickListener {
            jugador.setNombre(nombre.text.toString())
            jugador.setEdad(edad.text.toString().toInt())
            jugador.setPais(pais.text.toString())
            jugador.setEstatura(estatura.text.toString().toDouble())
            jugador.setFechaNacimiento(Date())  // Assume current date or convert from string
            jugador.setLesion(lesion.text.toString().equals("Sí", ignoreCase = true))

            val resultado = jugador.updateJugador()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
            irActividad(VerJugadores::class.java)
            finish()
        }

        val idEquipo = EquipoFutbol.idEquipoSelected
        val equipoAux = DbEquipoFutbol(null, "", "", "", 0.0, 0, Date(), false, this)

        val textViewEquipo = findViewById<TextView>(R.id.tv_titleEquipo)
        textViewEquipo.text = equipoAux.getEquipoFutbolById(idEquipo)?.getNombre()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cleanEditText() {
        val id = findViewById<EditText>(R.id.update_id_jugador)
        id.setText("")

        val nombre = findViewById<EditText>(R.id.update_nombre_jugador)
        nombre.setText("")

        val edad = findViewById<EditText>(R.id.update_edad_jugador)
        edad.setText("")

        val pais = findViewById<EditText>(R.id.update_pais_jugador)
        pais.setText("")

        val estatura = findViewById<EditText>(R.id.update_estatura_jugador)
        estatura.setText("")

        val fechaNacimiento = findViewById<EditText>(R.id.update_fecha_nacimiento_jugador)
        fechaNacimiento.setText("")

        val lesion = findViewById<EditText>(R.id.update_lesion_jugador)
        lesion.setText("")

        id.requestFocus()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
