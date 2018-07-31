package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades.{Instruccion, Pedido, Ruta}
import scala.io.Source
import java.io.PrintWriter

sealed trait AlgebraServicioRuta {
  def stringToRuta(string: String):Ruta
}

sealed trait InterpreteServiciosRuta extends AlgebraServicioRuta{
  override def stringToRuta(string: String): Ruta = {
    val list = string.toList.map(x => Instruccion.newInstruccion(x) )
    Ruta(list)
  }
}
object interpreteServiciosRuta extends InterpreteServiciosRuta


sealed trait AlgebraServicioArchivo{
  def stringToPedidos(string: String):Pedido
}

sealed trait interpreteServiciosPedidos extends AlgebraServicioArchivo{
  override def stringToPedidos(string: String): Pedido = {
    //Aqui leemos el in.txt
    /*val source = Source.fromFile(args(0))
    val lines = source.

    source.close
    val lineas = scala.io.Source.fromFile(" in.txt ").mkString
    println(lineas)*/

    Pedido(List(Ruta(List(Instruccion.newInstruccion('A'), Instruccion.newInstruccion('A') ))))
  }
}
