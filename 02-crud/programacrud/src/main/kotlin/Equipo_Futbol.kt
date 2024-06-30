import java.io.Serializable
import java.util.Date

data class Equipo_Futbol(
    var nombre: String,
    var pais: String,
    var federacion: String,
    var edadMedia: Double,
    var numeroTrofeos: Int,
    var fechaProximoJuego: Date,
    var campeonActual: Boolean,
    var jugadores: MutableList<Jugador> = mutableListOf()
) : Serializable