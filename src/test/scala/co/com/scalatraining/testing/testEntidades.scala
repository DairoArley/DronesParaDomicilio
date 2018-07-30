package co.com.scalatraining.testing

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

}
