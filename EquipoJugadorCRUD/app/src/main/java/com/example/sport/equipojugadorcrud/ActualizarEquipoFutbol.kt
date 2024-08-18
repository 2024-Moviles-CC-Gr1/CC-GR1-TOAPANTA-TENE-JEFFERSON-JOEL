package com.example.sport.equipojugadorcrud

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ActualizarEquipoFutbol : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_equipo_futbol)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idEquipo = EquipoFutbol.idEquipoSelected
        var equipo = DbEquipoFutbol(null, "", "", "", 0.0, 0, Date(), false, this)
        equipo = equipo.getEquipoFutbolById(idEquipo) ?: return

        val id = findViewById<EditText>(R.id.update_equipo)
        id.setText(equipo.getIdEquipo().toString())

        val nombre = findViewById<EditText>(R.id.update_nombreEquipo)
        nombre.setText(equipo.getNombre())

        val pais = findViewById<EditText>(R.id.update_paisEquipo)
        pais.setText(equipo.getPais())

        val federacion = findViewById<EditText>(R.id.update_federacionEquipo)
        federacion.setText(equipo.getFederacion())

        val edadMedia = findViewById<EditText>(R.id.update_edadMediaEquipo)
        edadMedia.setText(equipo.getEdadMedia().toString())

        val numeroTrofeos = findViewById<EditText>(R.id.update_numeroTrofeosEquipo)
        numeroTrofeos.setText(equipo.getNumeroTrofeos().toString())

        val fechaProximoJuego = findViewById<EditText>(R.id.update_fechaProximoJuegoEquipo)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        fechaProximoJuego.setText(dateFormat.format(equipo.getFechaProximoJuego()))

        val campeonActual = findViewById<Switch>(R.id.update_switchCampeonActualEquipo)
        campeonActual.isChecked = equipo.getCampeonActual()

        fechaProximoJuego.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${monthOfYear + 1}/$year"
                fechaProximoJuego.setText(selectedDate)
            }, year, month, day)
            dpd.show()
        }

        val btnActualizarEquipo = findViewById<Button>(R.id.btn_upd_equipo)
        btnActualizarEquipo.setOnClickListener {
            // Update EquipoFutbol
            equipo.setNombre(nombre.text.toString())
            equipo.setPais(pais.text.toString())
            equipo.setFederacion(federacion.text.toString())
            equipo.setEdadMedia(edadMedia.text.toString().toDouble())
            equipo.setNumeroTrofeos(numeroTrofeos.text.toString().toInt())
            equipo.setFechaProximoJuego(dateFormat.parse(fechaProximoJuego.text.toString()) ?: Date())
            equipo.setCampeonActual(campeonActual.isChecked)

            val resultado = equipo.updateEquipoFutbol()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
            irActividad(EquipoFutbol::class.java)
            finish()
        }
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
        val id = findViewById<EditText>(R.id.update_equipo)
        id.setText("")
        val nombre = findViewById<EditText>(R.id.update_nombreEquipo)
        nombre.setText("")
        val pais = findViewById<EditText>(R.id.update_paisEquipo)
        pais.setText("")
        val federacion = findViewById<EditText>(R.id.update_federacionEquipo)
        federacion.setText("")
        val edadMedia = findViewById<EditText>(R.id.update_edadMediaEquipo)
        edadMedia.setText("")
        val numeroTrofeos = findViewById<EditText>(R.id.update_numeroTrofeosEquipo)
        numeroTrofeos.setText("")
        val fechaProximoJuego = findViewById<EditText>(R.id.update_fechaProximoJuegoEquipo)
        fechaProximoJuego.setText("")
        val campeonActual = findViewById<Switch>(R.id.update_switchCampeonActualEquipo)
        campeonActual.isChecked = false
        id.requestFocus()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
