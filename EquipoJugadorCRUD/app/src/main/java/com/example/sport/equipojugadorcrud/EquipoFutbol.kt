package com.example.sport.equipojugadorcrud

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol
import java.util.Date

class EquipoFutbol : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    companion object {
        var idEquipoSelected = 0
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_futbol)
        showListViewEquipoFutbol()

        val nombre = findViewById<EditText>(R.id.editTextNombreEquipo)
        nombre.requestFocus()

        val pais = findViewById<EditText>(R.id.editTextPaisEquipo)
        val federacion = findViewById<EditText>(R.id.editTextFederacionEquipo)
        val edadMedia = findViewById<EditText>(R.id.editTextEdadMediaEquipo)
        val numeroTrofeos = findViewById<EditText>(R.id.editTextNumeroTrofeosEquipo)
        val fechaProximoJuego = findViewById<EditText>(R.id.editTextFechaProximoJuegoEquipo)
        val campeonActual = findViewById<Switch>(R.id.switchCampeonActualEquipo)
        val btnCrear = findViewById<Button>(R.id.btnCrearEquipo)

        fechaProximoJuego.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                // Formatea la fecha y colócala en el EditText
                val selectedDate = "${dayOfMonth}/${monthOfYear + 1}/$year"
                fechaProximoJuego.setText(selectedDate)
            }, year, month, day)

            dpd.show()
        }

        btnCrear.setOnClickListener {
            val equipoFutbol = DbEquipoFutbol(
                null,
                nombre.text.toString(),
                pais.text.toString(),
                federacion.text.toString(),
                edadMedia.text.toString().toDouble(),
                numeroTrofeos.text.toString().toInt(),
                Date(), // Use current date or convert from fechaProximoJuego.text.toString()
                campeonActual.isChecked,
                this
            )

            val result = equipoFutbol.insertEquipoFutbol()
            if (result > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewEquipoFutbol()
            } else {
                Toast.makeText(this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show()
            }
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Presiona nuevamente para salir", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editTextNombreEquipo)
        nombre.setText("")
        val pais = findViewById<EditText>(R.id.editTextPaisEquipo)
        pais.setText("")
        val federacion = findViewById<EditText>(R.id.editTextFederacionEquipo)
        federacion.setText("")
        val edadMedia = findViewById<EditText>(R.id.editTextEdadMediaEquipo)
        edadMedia.setText("")
        val numeroTrofeos = findViewById<EditText>(R.id.editTextNumeroTrofeosEquipo)
        numeroTrofeos.setText("")
        val fechaProximoJuego = findViewById<EditText>(R.id.editTextFechaProximoJuegoEquipo)
        fechaProximoJuego.setText("")
        val campeonActual = findViewById<Switch>(R.id.switchCampeonActualEquipo)
        campeonActual.isChecked = false
        nombre.requestFocus()
    }

    private fun showListViewEquipoFutbol() {
        val equipoFutbol = DbEquipoFutbol(null, "", "", "", 0.0, 0, Date(), false, this)
        val listViewEquipoFutbol = findViewById<ListView>(R.id.listView_EquipoFutbol)
        val adaptador = EquipoFutbolAdapter(this, equipoFutbol.showEquiposFutbol())
        listViewEquipoFutbol.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewEquipoFutbol)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_equipo_futbol, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val listViewEquipoFutbol = findViewById<ListView>(R.id.listView_EquipoFutbol)
        val equipoSeleccionado = listViewEquipoFutbol.adapter.getItem(info.position) as DbEquipoFutbol
        // Obtener el ID del equipo seleccionado
        idEquipoSelected = Integer.parseInt(equipoSeleccionado.getIdEquipo().toString()) - 1
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit_equipo_futbol -> {
                irActividad(ActualizarEquipoFutbol::class.java)
                true
            }
            R.id.action_delete_equipo_futbol -> {
                abrirDialogo()
                true
            }
            R.id.action_ver_jugadores -> {
                irActividad(VerJugadores::class.java)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este equipo de fútbol?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val equipoFutbol = DbEquipoFutbol(null, "", "", "", 0.0, 0, Date(), true, this)
                val resultado = equipoFutbol.deleteEquipoFutbol(idEquipoSelected)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewEquipoFutbol()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            }
        )
        builder.setNegativeButton("NO", null)

        val dialogo = builder.create()
        dialogo.show()
    }
}
