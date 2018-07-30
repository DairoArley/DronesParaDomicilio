package co.com.scalatraining.modelling.dominio.entidades


sealed trait Orientacion
case class Norte(char: Char) extends Orientacion
case class Sur(char: Char) extends Orientacion
case class Oeste(char: Char) extends Orientacion
case class Este(char: Char) extends Orientacion
//---------
case class Coordenada(x:Int, y:Int)

case class Ubicacion(coordenada: Coordenada, orientacion: Orientacion)

// Instrucciones

sealed trait Instruccion

object Instruccion {
  def newInstruccion(c:Char):Instruccion ={
    c match {
      case 'A' => A()
      case 'D' => D()
      case 'I' => I()
      case _ => throw new Exception(s"Caracter invalido para creacion de instruccion: $c")
    }
  }
}

case class A() extends Instruccion
case class I() extends Instruccion
case class D() extends Instruccion