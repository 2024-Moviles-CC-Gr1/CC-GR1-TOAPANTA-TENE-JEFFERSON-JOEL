package com.example.sport.equipojugadorcrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbJugador
import java.text.SimpleDateFormat
import java.util.Locale

class JugadorAdapter(context: Context, jugadores: ArrayList<DbJugador>) : ArrayAdapter<DbJugador>(context, 0, jugadores) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate the custom layout if necessary
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_jugador, parent, false)
        // Get the DbJugador object for the current position
        val jugador = getItem(position)

        // Find the TextViews in the layout
        val tvIdJugador = rowView.findViewById<TextView>(R.id.tvIdJugador)
        val tvNombreJugador = rowView.findViewById<TextView>(R.id.tvNombreJugador)
        val tvEdadJugador = rowView.findViewById<TextView>(R.id.tvEdadJugador)
        val tvPaisJugador = rowView.findViewById<TextView>(R.id.tvPaisJugador)
        val tvEstaturaJugador = rowView.findViewById<TextView>(R.id.tvEstaturaJugador)
        val tvFechaNacimientoJugador = rowView.findViewById<TextView>(R.id.tvFechaNacimientoJugador)
        val tvLesionJugador = rowView.findViewById<TextView>(R.id.tvLesionJugador)

        // Format the date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Assign the data to the TextViews
        tvIdJugador.text = jugador?.getidJugador().toString()
        tvNombreJugador.text = jugador?.getNombre()
        tvEdadJugador.text = jugador?.getEdad().toString()
        tvPaisJugador.text = jugador?.getPais()
        tvEstaturaJugador.text = jugador?.getEstatura().toString()
        tvFechaNacimientoJugador.text = jugador?.getFechaNacimiento()?.let { dateFormat.format(it) }
        tvLesionJugador.text = if (jugador?.getLesion() == true) "Sí" else "No"

        return rowView
    }
}
