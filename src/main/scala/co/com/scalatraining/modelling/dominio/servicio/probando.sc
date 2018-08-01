import co.com.scalatraining.modelling.dominio.entidades.{A, Entrega, Instruccion, Pedido}
import co.com.scalatraining.modelling.dominio.servicio.InterpreteServicioArchivo

val path = "/home/seven4/Documents/"
val fileName = "in01.txt"
val h = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
assert(h == List("AAAAIAAD","DDAIAD", "AIAIDDDAA") )

val j = List("AA")

//val list1 = h.map(x => x.toList)
//val list2 = list1

val pedido1 = j.map(x => x.toList)

assert(pedido1 == List(List('A', 'A') ) )
val pedido2 = pedido1.map( x => x.map( y => Instruccion.newInstruccion(y)))
assert(pedido2 == List(List(A(), A()) ) )
val list4 = pedido2.map(x => Entrega(x))
val pedido3 = Pedido(list4)
assert(pedido3 == Pedido(List(Entrega(List(Instruccion.newInstruccion('A' ) )) ) ) )

println(pedido3)