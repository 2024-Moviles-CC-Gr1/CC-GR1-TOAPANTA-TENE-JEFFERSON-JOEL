import java.io.*
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val equiposFutbol = cargarEquiposFutbol("equipo_futbol.txt")
    val jugadores = cargarJugadores("jugador.txt")

    loop@ while (true) {
        println("\n--- Programa CRUD (Equipo Futbol - Jugador) ---")
        print("Ingrese el numero correspondiente a su opcion: ")
        println("1. Crear")
        println("2. Leer")
        println("3. Actualizar")
        println("4. Eliminar")
        println("5. Finalizar Programa CRUD")


        when (scanner.nextInt()) {
            1 -> submenuCrear(equiposFutbol, jugadores, scanner)
            2 -> submenuLeer(equiposFutbol, jugadores, scanner)
            3 -> submenuActualizar(equiposFutbol, jugadores, scanner)
            4 -> submenuEliminar(equiposFutbol, jugadores, scanner)
            5 -> {
                guardarEquiposFutbol(equiposFutbol, "equipo_futbol.txt")
                guardarJugadores(jugadores, "jugador.txt")
                break@loop
            }
            else -> println("Opcion no valida. Por favor intente de nuevo.")
        }
    }

    println("¡Programa finalizado!")
} //Termina la funcion main

/////////////////////////////////////////////////////////////////////////////////////

//CARGA Y GUARDADO en los archivos
fun cargarEquiposFutbol(nombreArchivo: String): MutableList<Equipo_Futbol> {
    val file = File(nombreArchivo)
    if (file.length() == 0L) {
        return mutableListOf()
    }

    return try {
        ObjectInputStream(FileInputStream(file)).use { stream ->
            stream.readObject() as MutableList<Equipo_Futbol>
        }
    } catch (e: Exception) {
        e.printStackTrace()
        mutableListOf()
    }
}

fun guardarEquiposFutbol(equiposFutbol: MutableList<Equipo_Futbol>, nombreArchivo: String) {
    try {
        ObjectOutputStream(FileOutputStream(nombreArchivo)).use { stream ->
            stream.writeObject(equiposFutbol)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun cargarJugadores(nombreArchivo: String): MutableList<Jugador> {
    val file = File(nombreArchivo)
    if (file.length() == 0L) {
        return mutableListOf()
    }

    return try {
        ObjectInputStream(FileInputStream(file)).use { stream ->
            stream.readObject() as MutableList<Jugador>
        }
    } catch (e: Exception) {
        e.printStackTrace()
        mutableListOf()
    }
}

fun guardarJugadores(jugadores: MutableList<Jugador>, nombreArchivo: String) {
    try {
        ObjectOutputStream(FileOutputStream(nombreArchivo)).use { stream ->
            stream.writeObject(jugadores)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////

//SUBMENUS
fun submenuCrear(equiposFutbol: MutableList<Equipo_Futbol>, jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- CREAR ---")
    println("1. Crear Jugador")
    println("2. Crear Equipo de Futbol")
    print("Ingrese una opción: ")

    when (scanner.nextInt()) {
        1 -> {
            crearJugador(jugadores, scanner)
        }
        2 -> {
            val nuevoEquipoFutbol = crearEquipoFutbol(equiposFutbol, jugadores, scanner)
            equiposFutbol.add(nuevoEquipoFutbol)
            println("Equipos de Futbol creado con exito.")
        }
        else -> println("Opcion invalida. Por favor intente de nuevo.")
    }
}

fun submenuLeer(equiposFutbol: MutableList<Equipo_Futbol>, jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- LEER ---")
    println("1. Leer un equipo de futbol")
    println("2. Leer todos los jugadores")
    print("Ingrese una opción: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- LEER UN EQUIPO DE FUTBOL ---")
            println("Seleccione un equipo de futbol:")
            imprimirNombresEquiposFutbol(equiposFutbol)

            print("Ingrese el índice del equipo de futbol a leer: ")
            val indiceEquipoFutbol = scanner.nextInt()

            if (indiceEquipoFutbol in 0 until equiposFutbol.size) {
                val equipoFutbolSeleccionado = equiposFutbol[indiceEquipoFutbol]
                imprimirInformacionEquipoFutbol(equipoFutbolSeleccionado)
            } else {
                println("Indice inexistente.")
            }
        }
        2 -> {
            println("\n--- LEER UN JUGADOR ---")
            println("Seleccione un jugador:")
            imprimirNombresJugadores(jugadores)

            print("Ingrese el índice del jugador a leer: ")
            val indiceJugador = scanner.nextInt()

            if (indiceJugador in 0 until jugadores.size) {
                val jugadorSeleccionado = jugadores[indiceJugador]
                imprimirInformacionJugador(jugadorSeleccionado)
            } else {
                println("Indice inexistente.")
            }
        }
        else -> println("Opcion invalida. Por favor intente de nuevo.")
    }
}


fun submenuActualizar(equiposFutbol: MutableList<Equipo_Futbol>, jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- ACTUALIZAR ---")
    println("1. Actualizar informacion de un jugador")
    println("2. Actualizar informacion de un equipo de futbol")
    print("Ingrese una opcion: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- ACTUALIZAR INFORMACION DE UN JUGADOR ---")
            println("Seleccione un jugador para actualizar:")
            imprimirNombresJugadores(jugadores)

            print("Ingrese el indice del jugador a actualizar: ")
            val indiceJugador = scanner.nextInt()

            if (indiceJugador in 0 until jugadores.size) {
                val jugadorSeleccionado = jugadores[indiceJugador]
                actualizarInformacionJugador(jugadorSeleccionado, scanner)
            } else {
                println("Indice invalido.")
            }
        }
        2 -> {
            println("\n--- ACTUALIZAR INFORMACION DE UN EQUIPO DE FUTBOL ---")
            println("Seleccione un equipo de futbol para actualizar:")
            imprimirNombresEquiposFutbol(equiposFutbol)

            print("Ingrese el indice del equipo de futbol a actualizar: ")
            val indiceEquipoFutbol = scanner.nextInt()

            if (indiceEquipoFutbol in 0 until equiposFutbol.size) {
                val equipoFutbolSeleccionado = equiposFutbol[indiceEquipoFutbol]
                actualizarInformacionEquipoFutbol(equipoFutbolSeleccionado, jugadores, scanner)
            } else {
                println("Indice invalido.")
            }
        }
        else -> println("Opcion invalida. Por favor intente de nuevo.")
    }
}


fun submenuEliminar(equiposFutbol: MutableList<Equipo_Futbol>, jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- ELIMINAR ---")
    println("1. Eliminar jugador")
    println("2. Eliminar equipo de futbol")
    print("Ingrese una opcion: ")

    when (scanner.nextInt()) {
        1 -> {
            println("\n--- ELIMINAR JUGADOR ---")
            println("Seleccione un jugador para eliminar:")
            imprimirNombresJugadores(jugadores)

            print("Ingrese el indice del jugador a eliminar: ")
            val indiceJugador = scanner.nextInt()

            if (indiceJugador in 0 until jugadores.size) {
                eliminarJugador(jugadores, indiceJugador)
                println("Jugador eliminado con exito.")
            } else {
                println("Indice invalido.")
            }
        }
        2 -> {
            println("\n--- ELIMINAR EQUIPO DE FUTBOL ---")
            println("Seleccione un equipo de futbol para eliminar:")
            imprimirNombresEquiposFutbol(equiposFutbol)

            print("Ingrese el indice del equipo de futbol a eliminar: ")
            val indiceEquipoFutbol = scanner.nextInt()

            if (indiceEquipoFutbol in 0 until equiposFutbol.size) {
                eliminarEquipoFutbol(equiposFutbol, indiceEquipoFutbol)
                println("Equipo de futbol eliminado con exito.")
            } else {
                println("Indice invalido.")
            }
        }
        else -> println("Opcion invalida. Por favor intente de nuevo.")
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////
//CRUD
//-------------------------------------------------------------------------------------------------
//CREATE
fun crearEquipoFutbol(equiposFutbol: MutableList<Equipo_Futbol>, jugadores: MutableList<Jugador>, scanner: Scanner): Equipo_Futbol {
    println("\n--- CREAR EQUIPO DE FUTBOL ---")
    print("Nombre: ")
    val nombre = scanner.next()
    print("Pais: ")
    val pais = scanner.next()
    print("Federacion: ")
    val federacion = scanner.next()
    print("Edad media: ")
    val edadMedia = scanner.nextDouble()
    print("Numero (cantidad) de trofeos")
    val numeroTrofeos = scanner.nextInt()
    print("Fecha del proximo juego (yyyy-MM-dd): ")
    val fechaProximoJuegoString = scanner.next()
    val fechaProximoJuego = SimpleDateFormat("yyyy-MM-dd").parse(fechaProximoJuegoString)
    print("¿Es campeon actual en algun campeonato? (true/false): ")
    val campeonActual = scanner.nextBoolean()

    // Mostrar lista de jugadores disponibles
    println("\nJugadores disponibles:")
    imprimirNombresJugadores(jugadores)

    print("Ingrese los nombres de los jugadores que desea agregar separados por comas: ")
    val jugadoresSeleccionados = scanner.next().split(",").map { it.trim() }

    // Filtrar a los jugadores seleccionados de la lista completa
    val jugadoresAAgregar = jugadores.filter { it.nombre in jugadoresSeleccionados }.toMutableList()

    // Crear el nuevo equipo de futbol con los jugadores seleccionados
    val nuevoEquipoFutbol = Equipo_Futbol(nombre, pais, federacion, edadMedia, numeroTrofeos, fechaProximoJuego,
        campeonActual, jugadoresAAgregar)

    return nuevoEquipoFutbol
}

fun imprimirNombresJugadores(jugadores: MutableList<Jugador>) {
    jugadores.forEachIndexed { index, jugador ->
        println("$index. ${jugador.nombre}")
    }
}

fun crearJugador(jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- CREAR JUGADOR ---")
    print("Nombre: ")
    val nombre = scanner.next()
    print("Edad: ")
    val edad = scanner.nextInt()
    print("Pais: ")
    val pais = scanner.next()
    print("Estatura: ")
    val estatura = scanner.nextDouble()
    print("Fecha de nacimiento (yyyy-MM-dd): ")
    val fechaNacimientoString = scanner.next()
    val fechaNacimiento = SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoString)
    print("¿Presenta actualmente alguna lesion? (true/false): ")
    val lesion = scanner.nextBoolean()

    // Crear un nuevo jugador y agregarlo a la lista
    val nuevoJugador = Jugador(nombre, edad, pais, estatura, fechaNacimiento, lesion)
    jugadores.add(nuevoJugador)

    println("Jugador creado exitosamente.")
}
//--------------------------------------------------------------------------------------------
//READ
fun imprimirNombresEquiposFutbol(equiposFutbol: MutableList<Equipo_Futbol>) {
    equiposFutbol.forEachIndexed { index, equipoFutbol ->
        println("$index. ${equipoFutbol.nombre}")
    }
}

fun imprimirInformacionEquipoFutbol(equipoFutbol: Equipo_Futbol) {
    println("\n--- INFORMACION DEL EQUIPO DE FUTBOL ---")
    println("Nombre: ${equipoFutbol.nombre}")
    println("Pais: ${equipoFutbol.pais}")
    println("Federacion: ${equipoFutbol.federacion}")
    println("Edad media: ${equipoFutbol.edadMedia}")
    println("Numero de trofeos: ${equipoFutbol.numeroTrofeos}")
    println("Fecha del proximo juego: ${SimpleDateFormat("yyyy-MM-dd").format(equipoFutbol.fechaProximoJuego)}")
    println("Campeon actual en alguna competicion: ${equipoFutbol.campeonActual}")

    if (equipoFutbol.jugadores.isNotEmpty()) {
        println("\n--- Jugadores ---")
        equipoFutbol.jugadores.forEachIndexed { index, jugador ->
            println("$index. ${jugador.nombre} - Edad: ${jugador.edad} - Pais de nacimiento: ${jugador.pais}")
        }
    } else {
        println("\nNo hay jugadores en este equipo de futbol por el momento.")
    }
}

fun imprimirInformacionJugador(jugador: Jugador) {
    println("\n--- INFORMACION DEL JUGADOR ---")
    println("Nombre: ${jugador.nombre}")
    println("Edad: ${jugador.edad}")
    println("Pais de nacimiento: ${jugador.pais}")
    println("Estatura: ${jugador.estatura}")
    println("Fecha de nacimiento: ${SimpleDateFormat("yyyy-MM-dd").format(jugador.fechaNacimiento)}")
    println("Lesion: ${jugador.lesion}")
}
//------------------------------------------------------------------------------------------------------------
//UPDATE
fun actualizarInformacionJugador(jugador: Jugador, scanner: Scanner) {
    println("\n--- ACTUALIZAR INFORMACION DE UN JUGADOR ---")
    imprimirInformacionJugador(jugador)

    println("\nIngrese los nuevos datos del jugador:")
    print("Nuevo Nombre: ")
    jugador.nombre = scanner.next()
    print("Nueva Edad: ")
    jugador.edad = scanner.nextInt()
    print("Nuevo Pais: ")
    jugador.pais = scanner.next()
    print("Nueva Estatura: ")
    jugador.estatura = scanner.nextDouble()
    print("Nueva Fecha de nacimiento (yyyy-MM-dd): ")
    val nuevaFechaNacimientoString = scanner.next()
    jugador.fechaNacimiento = SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaNacimientoString)
    print("¿Presenta alguna lesion? (true/false): ")
    jugador.lesion = scanner.nextBoolean()

    println("La informacion del jugador ha sido actualizada exitosamente.")
}

fun actualizarInformacionEquipoFutbol(equipoFutbol: Equipo_Futbol, jugadores: MutableList<Jugador>, scanner: Scanner) {
    println("\n--- ACTUALIZAR INFORMACION DEL EQUIPO DEL FUTBOL ---")
    println("Equipo de futbol actual a actualizar:")
    imprimirInformacionEquipoFutbol(equipoFutbol)

    println("\nIngrese los nuevos datos del equipo de futbol:")
    print("\nNuevo Nombre: ")
    val nuevoNombre = scanner.next()
    equipoFutbol.nombre = nuevoNombre
    print("Nuevo Pais: ")
    val nuevoPais = scanner.next()
    equipoFutbol.pais = nuevoPais
    print("Nueva Federacion: ")
    val nuevaFederacion = scanner.next()
    equipoFutbol.federacion = nuevaFederacion
    print("Nueva Edad media: ")
    val nuevaEdadMedia = scanner.nextDouble()
    equipoFutbol.edadMedia = nuevaEdadMedia
    print("Nuevo Numero (cantidad) de trofeos")
    val nuevoNumeroTrofeos = scanner.nextInt()
    equipoFutbol.numeroTrofeos = nuevoNumeroTrofeos
    print("Nueva Fecha del proximo juego (yyyy-MM-dd): ")
    val nuevaFechaProximoJuegoString = scanner.next()
    val nuevaFechaProximoJuego = SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaProximoJuegoString)
    equipoFutbol.fechaProximoJuego = nuevaFechaProximoJuego
    print("¿Es campeon actual en algun campeonato? (true/false): ")
    val nuevoCampeonActual = scanner.nextBoolean()
    equipoFutbol.campeonActual = nuevoCampeonActual

    println("\nJugadores disponibles:")
    imprimirNombresJugadores(jugadores)

    print("Ingrese los nuevos jugadores separados por comas: ")
    val jugadoresSeleccionados = scanner.next().split(",").map { it.trim() }
    val jugadoresAAgregar = jugadores.filter { it.nombre in jugadoresSeleccionados }
    equipoFutbol.jugadores = jugadoresAAgregar.toMutableList()

    println("El equipo de futbol fue actualizado exitosamente.")
}
//------------------------------------------------------------------------------------------
//DELETE
fun eliminarJugador(jugadores: MutableList<Jugador>, indice: Int) {
    // Implementa la logica para eliminar el jugador en el indice escogido
    jugadores.removeAt(indice)
}

fun eliminarEquipoFutbol(equiposFutbol: MutableList<Equipo_Futbol>, indice: Int) {
    // Implementa la logica para eliminar el equipo de futbol en el indice escogido
    equiposFutbol.removeAt(indice)
}