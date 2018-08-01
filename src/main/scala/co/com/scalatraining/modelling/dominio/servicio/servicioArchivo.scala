package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades.{A, Entrega, Instruccion, Pedido}

import scala.io.Source

sealed trait AlgebraServicioArchivo{
  def leerArchivo(hilera:String):List[String]
  //def leerArchivos(list:String)
}

sealed trait InterpreteServicioArchivo extends AlgebraServicioArchivo {
  override def leerArchivo(hilera: String): List[String] = {

    val lista = Source.fromFile(hilera).getLines().toList
    lista
  }
}

object InterpreteServicioArchivo extends InterpreteServicioArchivo


