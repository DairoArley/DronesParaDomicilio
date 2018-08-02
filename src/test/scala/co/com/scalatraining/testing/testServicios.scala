package co.com.scalatraining.testing

import co.com.scalatraining.modelling.dominio.entidades._
import co.com.scalatraining.modelling.dominio.servicio.{InterpretacionServicioDrone, InterpreteServicioArchivo}
import org.scalatest.FunSuite

class testServicios extends FunSuite {

  test("test creancion de un Entrega"){
    val text = "AIAADAA"
    val list1 = text.toList
    assert(list1==List('A', 'I', 'A','A', 'D', 'A', 'A') )
    val list = text.toList.map(x => Instruccion.newInstruccion(x))
    Entrega(list)
    assert(list==List(A(), I(), A(), A(), D(), A(), A() ) )
  }

  test ("test leer archivo"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val h = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    assert(h == List("AAAAIAAD", "DDAIAD", "AAIADAD")  )
    val j = List("AA")
    val pedido1 = j.map(x => x.toList)
    assert(pedido1 == List(List('A', 'A') ) )
    val pedido2 = pedido1.map( x => x.map( y => Instruccion.newInstruccion(y)))
    assert(pedido2 == List(List(A(), A()) ) )
    val list4 = pedido2.map(x => Entrega(x))
    val pedido3 = Pedido(list4)
  }

  test("test Probando la funcionalidad"){
    val path = "/home/seven4/Documents/Pedidos/"
    val fileName = "in01.txt"
    val list = InterpreteServicioArchivo.leerArchivo(s"${path}${fileName}")
    val pedido = InterpretacionServicioDrone.tomarPedido(list)
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)
    val h = InterpretacionServicioDrone.llevarPedido(pedido, drone)
    assert(h == List(Reporte(-2, 4,Norte('N')), Reporte(-1,3, Sur('S')), Reporte(0,0, Oeste('O'))))
    val j = List("AA")
    val pedido1 = j.map(x => x.toList)
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

  test("Simulando Todo el programa"){
    val hola = InterpreteServicioArchivo.leerArchivo("/home/seven4/Documents/Pedidos/in01.txt")
    val hela = InterpretacionServicioDrone.tomarPedido(hola)
    val hila = InterpretacionServicioDrone.llevarPedido(hela, Drone(Ubicacion(Coordenada(0,2), Norte('N')), 10, 1) )
    assert(hila == List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O')))  )
  }

  test("Simulando Todo el programa varios archivos"){
    val hola = InterpreteServicioArchivo.leerArchivos("/home/seven4/Documents/Pedidos/")
    val hela = hola.map( x => InterpretacionServicioDrone.tomarPedido(x))
    val hila = hela.map( x => InterpretacionServicioDrone.llevarPedido(x, Drone(Ubicacion(Coordenada(0,2), Norte('N')), 10, 1) ) )
    assert(hila == List(List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))),
      List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')),
        Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')),
        Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))),
      List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')),
        Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')),
        Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))),
      List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')),
        Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')),
        Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))),
      List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')),
        Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')),
        Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))),
      List(Reporte(-2,6,Norte('N')), Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))), List(Reporte(-2,6,Norte('N')),
        Reporte(-1,5,Sur('S')), Reporte(0,2,Oeste('O'))))
    )
  }
}
