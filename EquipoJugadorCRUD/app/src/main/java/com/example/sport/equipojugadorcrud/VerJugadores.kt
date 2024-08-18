package com.example.sport.equipojugadorcrud

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbJugador
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol

class VerJugadores : AppCompatActivity() {

    companion object {
        var idJugadorSelected = 0
    }

    @SuppressLint("MissingFlattedID", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_jugadores)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idEquipo = EquipoFutbol.idEquipoSelected
        val equipoAux = DbEquipoFutbol(null, "", "", "", 0.0, 0, null, false, this)

        val textViewEquipo = findViewById<TextView>(R.id.ver_equipoPadre)
        textViewEquipo.text = equipoAux.getEquipoFutbolById(idEquipo)?.getNombre()

        val btnCrearJugador = findViewById<Button>(R.id.btn_crearJugador)
        btnCrearJugador.setOnClickListener {
            irActividad(Jugador::class.java)
        }

        showListView(idEquipo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                irActividad(EquipoFutbol::class.java)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showListView(id: Int) {
        val objetoJugador = DbJugador(null, "", 0, "", 0.0, null, false, this)
        val listViewJugador = findViewById<ListView>(R.id.listView_jugador)
        val adaptador = JugadorAdapter(this, objetoJugador.showJugadores())
        listViewJugador.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewJugador)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Inflate the context menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jugador, menu)

        // Get the selected item's information
        val info = menuInfo as AdapterView.AdapterContextMenuInfo

        // Get the selected DbJugador object
        val listViewJugador = findViewById<ListView>(R.id.listView_jugador)
        val jugadorSeleccionado = listViewJugador.adapter.getItem(info.position) as DbJugador

        // Get the ID of the selected player
        idJugadorSelected = jugadorSeleccionado.getidJugador() ?: 0
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit_jugador -> {
                irActividad(ActualizarJugador::class.java)
                true
            }
            R.id.action_delete_jugador -> {
                abrirDialogo()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este jugador?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val jugador = DbJugador(null, "", 0, "", 0.0, null, false, this)
                val resultado = jugador.deleteJugador(idJugadorSelected)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idEquipo = EquipoFutbol.idEquipoSelected
                showListView(idEquipo)
            }
        )
        builder.setNegativeButton("NO", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
