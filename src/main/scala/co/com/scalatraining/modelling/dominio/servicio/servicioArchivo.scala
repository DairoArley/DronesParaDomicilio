package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades.{A, Entrega, Instruccion, Pedido}

import scala.io.Source

sealed trait AlgebraServicioArchivo{
  def leerArchivo(hilera:String): Pedido
}

sealed trait InterpreteServicioArchivo extends AlgebraServicioArchivo {
  override def leerArchivo(hilera: String): Pedido = {
    val source = Source.fromFile(hilera)
    val lines = source.getLines()
    println(lines)
    Pedido(List(Entrega(List(Instruccion.newInstruccion('A')))))
  }
}

object interpreteServiciosArchivo extends InterpreteServicioArchivo


/*sealed trait interpreteServiciosPedidos extends AlgebraServicioArchivo{
  override def stringToPedidos(string: String): Pedido = {
    //Aqui leemos el in.txt
    /*val source = Source.fromFile(args(0))
    val lines = source.

    source.close
    val lineas = scala.io.Source.fromFile(" in.txt ").mkString
    println(lineas)*/

    Pedido(List(Entrega(List(Instruccion.newInstruccion('A'), Instruccion.newInstruccion('A') ))))
  }*/

