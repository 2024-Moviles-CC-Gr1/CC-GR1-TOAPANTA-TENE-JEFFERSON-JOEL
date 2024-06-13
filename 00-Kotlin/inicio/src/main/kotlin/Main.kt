import java.util.*
import javax.naming.spi.InitialContextFactory
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo")
    //VARIABLES INMUTABLES (No se reasigna "=")
    val inmutable: String = "Jefferson";
    //inmutable = "Joel" // Error!
    //MUTABLES
    var mutable: String = "Joel"
    mutable = "Jefferson"
    // VAL > VAR (mejor que)
    // Tener mas variables mutables puede dar lugares a bugs
    // Duck Typing (Si es que algo se parece a pato, es un pato)
    val ejemploVariable = " Jefferson Toapanta " //No necesariamente hay que poner el tipo (Duck Typing)
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejemploVariable = edadEjemplo // Error!
    //Variables Primitivas
    val nombrePrefesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad:Boolean = true
    //Clases en Java
    val fechaNacimiento: Date = Date()

    //When (Que es como un Switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") ->{
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else ->{
            println("No sabemos")
        }
    }
    val esSoletro = (estadoCivilWhen == "S")
    //val esCasado = (estadoCivilWhen == "C")
    val coqueteo = if (esSoletro) "Si" else "No" //if else chiquito

    //val coqueteo1 = if (esSoletro) "Si" else if (esCasado) "No" else "Nose" //No vale el elseif

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    //Named parameters
    // calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma(1,1) //new Suma(1,1) en KOTLIN no hay "new"
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    // Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico);
    //Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OJO SOLO PARA MEJOR RENDIMIENTO SE APLICA EL FOR O WHILE, CASO CONTRARIO NO DEBERIAN ESTAR
    //FOR EACH = > Unit
    //Iterar un arrgeglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->  // -> = >
            println("Valor actual: ${valorActual}");
        }

    // "it" (en ingles "eso") significa el elemento iterado
    arregloDinamico.forEach{ println("Valor actual (it): ${it}") }

    //MAP -> MUTA(Modifica cambia) el arreglo
    //1) Enviamos en nuevo valor de la iteracion
    //2) Nos devuelve un NUEVO ARREGLO con valores
    // de las iteraciones
    val respuestaMAP: List<Double> = arregloDinamico //transformar el arreglo entero a double
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMAP)
    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)

    //Filter -> Filtrar el ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo FILTRADO
    val  respuestaFilter: List<Int> = arregloDinamico
        .filter{ valorActual:Int ->
            //Expresion o CONDICION
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it < 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    //OR -> ANY (Alguno Cumple?)
    //AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual:Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //True
    val respuestaAll: Boolean = arregloDinamico
        .all{ valorActual:Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //False

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre empieza en 0 en Kotlin)
    // [1,2,3,4,5] -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 -> Iteracion1
    // valorIteracion2 = valorAcumuladoIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorAcumuladoIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // valorIteracion4 = valorAcumuladoIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // valorIteracion5 = valorAcumuladoIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5
    val respuestaReduce: Int = arregloDinamico
        .reduce{ acumulado:Int, valorActual:Int ->
            return@reduce (acumulado + valorActual) // -> Cambiar o usar la logica de negocio
        }
    println(respuestaReduce);
    //return@reduce acumulado + (itemCarrito.cantidad * itemCarrito.precio)

} //Termina la función main




//void -> Unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")//Template Strings //No hace falta concatenar
}
fun calcularSueldo(
    sueldo:Double, //Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial:Double? = null, // Opcional (nullable)
    //Variable? -> "?" Es Nullable (osea que puede en algun momento ser nullo)
):Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo *(100/tasa) * bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( //Constructor Primario
    //Caso 1) Parametro normal
    //uno:Int , (parametro (sin modificar acceso))

    //Caso 2) Parametro y propiedad (atributo)
    //private var uno: Int (propiedad "instancia.uno")

    //Caso 3) Parametro y propiedad publica (atributo)
    //var uno:Int (propiedad "instancia.uno") (public)
    protected val numeroUno: Int, //instancia.numeroUno
    protected val numeroDos: Int, //instancia.numeroDos
){
    init { //bloque constructor primario
        println("Inicializando")
    }
}

class Suma( //Constructor primario
    unoParametro: Int, //Parametro
    dosParametro: Int, //Parametros
): Numeros( // Clase papa, Numeros (extendiendo)
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito:String = "Explicito" // Publicos
    val soyPublicoImplicito:String = "Implicito" //Publicos  Public OPCIONAL(propiedades, metodos)
    init { // Bloque Codigo Constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCIONAL (propiedades, metodos)
        numeroDos // this. OPCIONAL (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL (propiedades, metodos)
    }

    //public fun sumar():Int{ (opcional "public")
    constructor( //Constructor secundario
        uno:Int?,
        dos:Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )
    constructor( //Constructor tercero
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if (dos==null) 0 else dos,
    )

    constructor( //Constructor cuarto
        uno:Int?,
        dos:Int?
    ):this(
        if(uno== null) 0 else uno,
        if(dos== null) 0 else dos,
    )

    fun sumar():Int{
        val total = numeroUno + numeroDos
        // Suma agregarHistorial(total) ("Suma." o "NombreClase." es OPCIONAL)
        agregarHistorial(total)
        return total
    }
    companion object{ //Comparte entre todas las interacciones, similar al Static
        //funciones y variables
        val pi = 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}