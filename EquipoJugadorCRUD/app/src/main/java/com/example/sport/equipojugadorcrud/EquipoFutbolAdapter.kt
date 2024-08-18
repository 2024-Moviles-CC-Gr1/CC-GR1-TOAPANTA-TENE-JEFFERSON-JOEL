package com.example.sport.equipojugadorcrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sport.deporteatletacrud.R
import com.example.sport.equipojugadorcrud.db.DbEquipoFutbol
import java.text.SimpleDateFormat
import java.util.Locale

class EquipoFutbolAdapter(context: Context, equipos: ArrayList<DbEquipoFutbol>) : ArrayAdapter<DbEquipoFutbol>(context, 0, equipos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate the custom layout if necessary
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_equipo_futbol, parent, false)
        // Get the DbEquipoFutbol object for the current position
        val equipo = getItem(position)

        // Find the TextViews in the layout
        val idEquipo = rowView.findViewById<TextView>(R.id.tvIdEquipo)
        val tvNombreEquipo = rowView.findViewById<TextView>(R.id.tvNombreEquipo)
        val tvPais = rowView.findViewById<TextView>(R.id.tvPaisEquipo)
        val tvFederacion = rowView.findViewById<TextView>(R.id.tvFederacionEquipo)
        val tvEdadMedia = rowView.findViewById<TextView>(R.id.tvEdadMediaEquipo)
        val tvNumeroTrofeos = rowView.findViewById<TextView>(R.id.tvNumeroTrofeosEquipo)
        val tvFechaProximoJuego = rowView.findViewById<TextView>(R.id.tvFechaProximoJuegoEquipo)
        val tvCampeonActual = rowView.findViewById<TextView>(R.id.tvCampeonActualEquipo)

        // Format the date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Assign the data to the TextViews
        idEquipo.text = equipo?.getIdEquipo().toString()
        tvNombreEquipo.text = equipo?.getNombre()
        tvPais.text = equipo?.getPais()
        tvFederacion.text = equipo?.getFederacion()
        tvEdadMedia.text = equipo?.getEdadMedia().toString()
        tvNumeroTrofeos.text = equipo?.getNumeroTrofeos().toString()
        tvFechaProximoJuego.text = equipo?.getFechaProximoJuego()?.let { dateFormat.format(it) }
        tvCampeonActual.text = if (equipo?.getCampeonActual() == true) "Sí" else "No"

        return rowView
    }
}
