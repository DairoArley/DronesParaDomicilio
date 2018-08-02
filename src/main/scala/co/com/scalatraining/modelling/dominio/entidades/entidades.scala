package co.com.scalatraining.modelling.dominio.entidades

import scala.util.Try

//Sustantivos

sealed trait Orientacion
case class Norte(char: Char) extends Orientacion
case class Sur(char: Char) extends Orientacion
case class Oeste(char: Char) extends Orientacion
case class Este(char: Char) extends Orientacion

case class Coordenada(x:Int, y:Int)

case class Entrega(list: List[Instruccion ])

case class Ubicacion(coordenada: Coordenada, orientacion: Orientacion)

sealed trait Instruccion
case class A() extends Instruccion
case class I() extends Instruccion
case class D() extends Instruccion

object Instruccion {
  def newInstruccion(c: Char): Instruccion = {
    c match {
      case 'A' => A()
      case 'D' => D()
      case 'I' => I()
    }
  }
}

case class Drone(ubicacion: Ubicacion, capacidad:Int, id:Int)

case class Recorrido(list: List[Ubicacion] )

case class Pedido(list: List[Entrega])

case class Reporte(x:Int, y:Int, orientacion: Orientacion)




