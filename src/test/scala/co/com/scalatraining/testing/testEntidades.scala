package co.com.scalatraining.testing

import co.com.scalatraining.modelling.dominio.entidades.{Coordenada, Norte, Orientacion, Ubicacion}
import org.scalatest.FunSuite

class testEntidades extends FunSuite {

  test("Probando"){
    val drone = "Nada"
    assert(drone === "Nada")
  }

  test("comprobando"){
    val text = "AIAADAA"
    val list1 = text.toList
    assert(list1==List("A", "I", "A","A", "D", "A", "A") )
  }

  test ("Hola"){
    val ubicacion = Ubicacion(Coordenada(1,2), Norte('N'))
    println(ubicacion)
  }
  
  test ("si"){
    val ubicacion = Ubicacion(Coordenada(1,2), Norte('N'))
    
  }

}
