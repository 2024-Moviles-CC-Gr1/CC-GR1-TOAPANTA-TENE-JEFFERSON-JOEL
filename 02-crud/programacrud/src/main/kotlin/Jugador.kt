import java.io.Serializable
import java.util.Date

data class Jugador(
    var nombre: String,
    var edad: Int,
    var pais: String,
    var estatura: Double,
    var fechaNacimiento: Date,
    var lesion: Boolean
) : Serializable