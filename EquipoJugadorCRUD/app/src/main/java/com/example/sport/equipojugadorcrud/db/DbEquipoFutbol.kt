package com.example.sport.equipojugadorcrud.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.Date

class DbEquipoFutbol(
    private var idEquipo: Int?,
    private var nombre: String,
    private var pais: String,
    private var federacion: String,
    private var edadMedia: Double,
    private var numeroTrofeos: Int,
    private var fechaProximoJuego: Date?,
    private var campeonActual: Boolean,
    private val context: Context
) {

    // Property Getters
    fun getIdEquipo(): Int? = idEquipo
    fun getNombre(): String = nombre
    fun getPais(): String = pais
    fun getFederacion(): String = federacion
    fun getEdadMedia(): Double = edadMedia
    fun getNumeroTrofeos(): Int = numeroTrofeos
    fun getFechaProximoJuego(): Date? = fechaProximoJuego
    fun getCampeonActual(): Boolean = campeonActual

    // Property Setters
    fun setIdEquipo(idEquipo: Int?) {
        this.idEquipo = idEquipo
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setPais(pais: String) {
        this.pais = pais
    }

    fun setFederacion(federacion: String) {
        this.federacion = federacion
    }

    fun setEdadMedia(edadMedia: Double) {
        this.edadMedia = edadMedia
    }

    fun setNumeroTrofeos(numeroTrofeos: Int) {
        this.numeroTrofeos = numeroTrofeos
    }

    fun setFechaProximoJuego(fechaProximoJuego: Date?) {
        this.fechaProximoJuego = fechaProximoJuego
    }

    fun setCampeonActual(campeonActual: Boolean) {
        this.campeonActual = campeonActual
    }

    // Database Operations
    fun insertEquipoFutbol(): Long {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("pais", pais)
            put("federacion", federacion)
            put("edad_media", edadMedia)
            put("numero_trofeos", numeroTrofeos)
            put("fecha_proximo_juego", fechaProximoJuego?.time ?: 0) // Convert Date to Long
            put("campeon_actual", if (campeonActual) 1 else 0) // Convert Boolean to Int
        }
        return db.insert("t_equipo_futbol", null, values)
    }

    fun showEquiposFutbol(): ArrayList<DbEquipoFutbol> {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val listaEquipos = ArrayList<DbEquipoFutbol>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM t_equipo_futbol", null)

        if (cursor.moveToFirst()) {
            do {
                val equipo = DbEquipoFutbol(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id_equipo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("pais")),
                    cursor.getString(cursor.getColumnIndexOrThrow("federacion")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("edad_media")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("numero_trofeos")),
                    Date(cursor.getLong(cursor.getColumnIndexOrThrow("fecha_proximo_juego"))), // Convert Long to Date
                    cursor.getInt(cursor.getColumnIndexOrThrow("campeon_actual")) == 1, // Convert Int to Boolean
                    context
                )
                listaEquipos.add(equipo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return listaEquipos
    }

    fun getEquipoFutbolById(id: Int): DbEquipoFutbol? {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM t_equipo_futbol WHERE id_equipo = ?", arrayOf(id.toString()))
        var equipo: DbEquipoFutbol? = null

        if (cursor.moveToFirst()) {
            equipo = DbEquipoFutbol(
                cursor.getInt(cursor.getColumnIndexOrThrow("id_equipo")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("pais")),
                cursor.getString(cursor.getColumnIndexOrThrow("federacion")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("edad_media")),
                cursor.getInt(cursor.getColumnIndexOrThrow("numero_trofeos")),
                Date(cursor.getLong(cursor.getColumnIndexOrThrow("fecha_proximo_juego"))), // Convert Long to Date
                cursor.getInt(cursor.getColumnIndexOrThrow("campeon_actual")) == 1, // Convert Int to Boolean
                context
            )
        }

        cursor.close()
        return equipo
    }

    fun updateEquipoFutbol(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("pais", pais)
            put("federacion", federacion)
            put("edad_media", edadMedia)
            put("numero_trofeos", numeroTrofeos)
            put("fecha_proximo_juego", fechaProximoJuego?.time ?: 0) // Convert Date to Long
            put("campeon_actual", if (campeonActual) 1 else 0) // Convert Boolean to Int
        }

        return db.update("t_equipo_futbol", values, "id_equipo=?", arrayOf(idEquipo.toString()))
    }

    fun deleteEquipoFutbol(id: Int): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("t_equipo_futbol", "id_equipo=?", arrayOf(id.toString()))
    }

    override fun toString(): String {
        return "ID Equipo: $idEquipo\nNombre: $nombre\nPaís: $pais\nFederación: $federacion\nEdad Media: $edadMedia\nNúmero de Trofeos: $numeroTrofeos\nFecha Próximo Juego: $fechaProximoJuego\nCampeón Actual: $campeonActual\n"
    }
}
