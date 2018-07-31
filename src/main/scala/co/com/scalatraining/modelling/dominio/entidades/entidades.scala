package co.com.scalatraining.modelling.dominio.entidades

sealed trait Orientacion
case class Norte(char: Char) extends Orientacion
case class Sur(char: Char) extends Orientacion
case class Oeste(char: Char) extends Orientacion
case class Este(char: Char) extends Orientacion

case class Coordenada(x:Int, y:Int)

case class Ubicacion(coordenada: Coordenada, orientacion: Orientacion)

sealed trait Instruccion
case class A() extends Instruccion
case class I() extends Instruccion
case class D() extends Instruccion

case class Drone(ubicacion: Ubicacion)

case class Entrega(string: String)
