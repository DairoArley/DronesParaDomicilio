package co.com.scalatraining.testing

import co.com.scalatraining.modelling.dominio.entidades._
import co.com.scalatraining.modelling.dominio.servicio.{InterpretacionServicioDrone}
import org.scalatest.FunSuite

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
    Ruta(list)
    assert(list==List(A(), I(), A(), A(), D(), A(), A() ) )

    //val lineas = scala.io.Source.fromFile(" in.txt ").getLines()
    //println(lineas)
  }

  test("Probando funcinaolidad de servicios drone"){


    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)

    val aux = InterpretacionServicioDrone.moverDrone(Instruccion.newInstruccion('A'), Drone(Ubicacion( Coordenada(0,0), Norte('N')), 1,1) )
    assert(aux==Drone(Ubicacion(Coordenada(0,1), Norte('N')), 1,1) )
  }

  test("creando drones y jbvdjbsduivcu"){
    val ruta = Ruta(List(A(), I(), A(), A(), D(), A(), A() ))
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)
    val hola = InterpretacionServicioDrone.llevarAlmuerzo(ruta, drone)
    assert(hola == Reporte(-2, 3, Norte('N') ) )
  }

  test ("Testiando tjndvsknksdvk"){
    val ruta = Ruta(List(A(), I(), A(), A(), D(), A(), A() ))
    val drone = Drone(Ubicacion(Coordenada(0,0), Norte('N')), 10, 1)
    val pasos = List(Drone)





  }




}
