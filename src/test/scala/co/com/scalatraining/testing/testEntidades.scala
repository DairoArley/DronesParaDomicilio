package co.com.scalatraining.testing

import co.com.scalatraining.modelling.dominio.entidades._
import co.com.scalatraining.modelling.dominio.servicio.{InterpretacionServicioDrone, InterpreteServicioArchivo}
import org.scalatest.FunSuite

import scala.util.Try

class testEntidades extends FunSuite {

  test("Probando"){
    val drone = "Nada"
    assert(drone === "Nada")
  }

  test("comprobando"){
    val text = "AIAADAA"
    val list1 = text.toList
    assert(list1==List('A', 'I', 'A','A', 'D', 'A', 'A') )
    val list = text.toList.map(x => Instruccion.newInstruccion(x))
    Entrega(list)
    assert(list==List(A(), I(), A(), A(), D(), A(), A() ) )

    //val lineas = scala.io.Source.fromFile(" in.txt ").getLines()
    //println(lineas)
  }

  test("Probando funcinaolidad de servicios drone"){


    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)

    //val aux = InterpretacionServicioDrone.moverDrone(Instruccion.newInstruccion('A'), Try{Drone(Ubicacion(Coordenada(0,1), Norte('N')), 1,1) } )
    //assert(aux==Try{Drone(Ubicacion(Coordenada(0,1), Norte('N')), 1,1) })
  }

  test ("Testiando tjndvsknksdvk"){
    //val entrega = Entrega(List(A(), I(), A(), A(), D(), A(), A() ))
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)
    val pasos = List(Drone)

    //val recorrido = List(drone)
    //val liposAct = entrega.list.foldLeft(recorrido){(resultado, item) => resultado :+ InterpretacionServicioDrone.llevarAlmuerzo(item, resultado.last)}

    //val hola = InterpretacionServicioDrone.llevarEntrega(entrega,Try{drone})
    //assert(hola == Reporte(-2, -2, Norte('N') ) )
  }

  test ("leer archivo"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val h = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    //assert(h == List("AAIAAD","DAIAD", "AIAAA") )

    val j = List("AA")

    //val list1 = h.map(x => x.toList)
    //val list2 = list1

    val pedido1 = j.map(x => x.toList)

    assert(pedido1 == List(List('A', 'A') ) )
    val pedido2 = pedido1.map( x => x.map( y => Instruccion.newInstruccion(y)))
    assert(pedido2 == List(List(A(), A()) ) )
    val list4 = pedido2.map(x => Entrega(x))
    val pedido3 = Pedido(list4)

    println(pedido3)

  }

  test("Probando todo"){

    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    val pedido = InterpretacionServicioDrone.tomarPedido(list)
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)

    //val h = InterpretacionServicioDrone.llevarPedido(pedido, drone)
    //assert(h == List(Reporte(-2, 4,Norte('N')), Reporte(-1,3, Sur('S')), Reporte(0,0, Oeste('O'))))

    val j = List("AA")
    //val ja = Entrega(List(A(), A() ) )

    val pedido1 = j.map(x => x.toList)
    //val jgjj = InterpretacionServicioDrone.llevarEntrega(ja , Try{drone})
    //assert(jgjj == Drone(Ubicacion(Coordenada(0,2), Norte('N')), 10, 1) )

    //assert(h == List(Reporte(2,3, Norte('N')) ) )
  }

  test("Falla de un Objecto compuesto por un atributo failure"){
    val uno = Try{Ubicacion}
    val dos = uno.isFailure
    //assert(uno == dos)
  }

  test("test se puede leer archivo de texto"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    assert(list == List("AAAAIAAD", "DDAIAD", "AAIADAD") )
  }

  test("test se puede crear un pedido"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")

    val pedido = InterpretacionServicioDrone.tomarPedido(list)
    assert(pedido == Pedido(List(Entrega(List(A(), A(), A(), A(), I(), A(), A(), D())), Entrega(List(D(), D(), A(), I(), A(), D())), Entrega(List(A(), A(), I(), A(), D(), A(), D())))) )
  }

  test("test Un pedido no valido"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")

    val pedido = InterpretacionServicioDrone.tomarPedido(list)

  }

  test("Probando todo again"){

    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    assert(list == List("AAAAIAAD", "DDAIAD", "AAIADAD") )
    val pedido = InterpretacionServicioDrone.tomarPedido(list)
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)

    //val h = InterpretacionServicioDrone.llevarPedido(pedido, drone)
    //assert(h == List(Reporte(-2, 4,Norte('N')), Reporte(-1,3, Sur('S')), Reporte(0,0, Oeste('O'))))

    val j = List("AA")
    //val ja = Entrega(List(A(), A() ) )

    val pedido1 = j.map(x => x.toList)
    //val jgjj = InterpretacionServicioDrone.llevarEntrega(ja , Try{drone})
    //assert(jgjj == Drone(Ubicacion(Coordenada(0,2), Norte('N')), 10, 1) )

    //assert(h == List(Reporte(2,3, Norte('N')) ) )
  }
  test("Simulando mdffdain"){
    //val primero = InterpreteServicioArchivo.leerArchivos("/home/seven4/Documents/Pedidos/")
    //val segundo: List[Pedido] = InterpretacionServicioDrone.tomarPedidos(primero)
    //val tres =
    val hola = InterpreteServicioArchivo.leerArchivo("/home/seven4/Documents/Pedidos/in01.txt")
    val hela = InterpretacionServicioDrone.tomarPedido(hola)
    val hila = InterpretacionServicioDrone.llevarPedido(hela, Drone(Ubicacion(Coordenada(0,2), Norte('N')), 10, 1) )
  }


}
