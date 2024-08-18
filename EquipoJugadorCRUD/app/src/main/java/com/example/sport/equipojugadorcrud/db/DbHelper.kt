package com.example.sport.equipojugadorcrud.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?): SQLiteOpenHelper(context, "DBExamen1.db", null, 3) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEquipoFutbol =
            """
            CREATE TABLE t_equipo_futbol(
                id_equipo INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                pais TEXT NOT NULL,
                federacion TEXT NOT NULL,
                edad_media DOUBLE NOT NULL,
                numero_trofeos INTEGER NOT NULL,
                fecha_proximo_juego INTEGER NOT NULL,  -- Stored as UNIX timestamp
                campeon_actual INTEGER NOT NULL  -- 1 for true, 0 for false
            );
            """.trimIndent()

        val scriptSQLCrearTablaJugador =
            """
            CREATE TABLE t_jugador(
                id_jugador INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                edad INTEGER NOT NULL,
                pais TEXT NOT NULL,
                estatura DOUBLE NOT NULL,
                fecha_nacimiento INTEGER NOT NULL,  -- Stored as UNIX timestamp
                lesion INTEGER NOT NULL  -- 1 for true, 0 for false
            );
            """.trimIndent()

        db?.execSQL(scriptSQLCrearTablaEquipoFutbol)
        db?.execSQL(scriptSQLCrearTablaJugador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            // Example for adding a new column (no changes needed in this case)
        }
    }
}
