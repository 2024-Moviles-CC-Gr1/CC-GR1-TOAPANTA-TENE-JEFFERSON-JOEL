package com.example.sport.equipojugadorcrud.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.Date

class DbJugador(
    private var idJugador: Int?,
    private var nombre: String,
    private var edad: Int,
    private var pais: String,
    private var estatura: Double,
    private var fechaNacimiento: Date?,
    private var lesion: Boolean,
    private val context: Context
) {

    // Property Setters
    fun setidJugador(idJugador: Int?) {
        this.idJugador = idJugador
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setEdad(edad: Int) {
        this.edad = edad
    }

    fun setPais(pais: String) {
        this.pais = pais
    }

    fun setEstatura(estatura: Double) {
        this.estatura = estatura
    }

    fun setFechaNacimiento(fechaNacimiento: Date) {
        this.fechaNacimiento = fechaNacimiento
    }

    fun setLesion(lesion: Boolean) {
        this.lesion = lesion
    }

    // Property Getters
    fun getidJugador(): Int? = this.idJugador

    fun getNombre(): String = this.nombre

    fun getEdad(): Int = this.edad

    fun getPais(): String = this.pais

    fun getEstatura(): Double = this.estatura

    fun getFechaNacimiento(): Date? = this.fechaNacimiento

    fun getLesion(): Boolean = this.lesion

    // Database Operations
    fun insertJugador(): Long {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("edad", edad)
            put("pais", pais)
            put("estatura", estatura)
            put("fecha_nacimiento", fechaNacimiento!!.time) // Store as a long
            put("lesion", lesion)
        }
        return db.insert("t_jugador", null, values)
    }

    fun showJugadores(): ArrayList<DbJugador> {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val listaJugadores = ArrayList<DbJugador>()
        val cursorJugador: Cursor = db.rawQuery("SELECT * FROM t_jugador", null)

        if (cursorJugador.moveToFirst()) {
            do {
                val jugador = DbJugador(
                    cursorJugador.getInt(cursorJugador.getColumnIndexOrThrow("id_jugador")),
                    cursorJugador.getString(cursorJugador.getColumnIndexOrThrow("nombre")),
                    cursorJugador.getInt(cursorJugador.getColumnIndexOrThrow("edad")),
                    cursorJugador.getString(cursorJugador.getColumnIndexOrThrow("pais")),
                    cursorJugador.getDouble(cursorJugador.getColumnIndexOrThrow("estatura")),
                    Date(cursorJugador.getLong(cursorJugador.getColumnIndexOrThrow("fecha_nacimiento"))),
                    cursorJugador.getInt(cursorJugador.getColumnIndexOrThrow("lesion")) == 1,
                    context
                )
                listaJugadores.add(jugador)
            } while (cursorJugador.moveToNext())
        }

        cursorJugador.close()
        return listaJugadores
    }

    fun getJugadorById(id: Int): DbJugador? {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM t_jugador WHERE id_jugador = ?", arrayOf(id.toString()))
        var jugador: DbJugador? = null

        if (cursor.moveToFirst()) {
            jugador = DbJugador(
                cursor.getInt(cursor.getColumnIndexOrThrow("id_jugador")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                cursor.getString(cursor.getColumnIndexOrThrow("pais")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("estatura")),
                Date(cursor.getLong(cursor.getColumnIndexOrThrow("fecha_nacimiento"))),
                cursor.getInt(cursor.getColumnIndexOrThrow("lesion")) == 1,
                context
            )
        }

        cursor.close()
        return jugador
    }

    fun updateJugador(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("edad", edad)
            put("pais", pais)
            put("estatura", estatura)
            put("fecha_nacimiento", fechaNacimiento!!.time)
            put("lesion", lesion)
        }

        return db.update("t_jugador", values, "id_jugador=?", arrayOf(idJugador.toString()))
    }

    fun deleteJugador(id: Int): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("t_jugador", "id_jugador=?", arrayOf(id.toString()))
    }

    override fun toString(): String {
        return "id Jugador: $idJugador\nNombre: $nombre\nEdad: $edad\nPaís: $pais\nEstatura: $estatura\nFecha Nacimiento: $fechaNacimiento\nLesion: $lesion\n"
    }
}
