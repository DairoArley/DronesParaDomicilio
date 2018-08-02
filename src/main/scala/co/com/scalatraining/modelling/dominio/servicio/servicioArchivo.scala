package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades._
import java.io.{File, PrintWriter}

import scala.io.Source

sealed trait AlgebraServicioArchivo{
  def leerArchivo(hilera:String):List[String]
  def leerArchivos(list:String):List[List[String] ]
  def entregarReporte(posiciones:List[Drone])

}

sealed trait InterpreteServicioArchivo extends AlgebraServicioArchivo {
  override def leerArchivo(hilera: String): List[String] = {

    val lista = Source.fromFile(hilera).getLines().toList
    lista
  }

  override def leerArchivos(list: String): List[List[String] ] = {
    //val raiz = "/home/seven4/Documents/Pedidos/"
    val listaIdDrones = List.range(1,20)
    val listadrones = listaIdDrones.map( x => Drone(Ubicacion(Coordenada(0,0),Norte('N')),1,x))
    val listaText = listaIdDrones.map( x => s"in0${x}.txt")
    listaText.map(x => InterpreteServicioArchivo.leerArchivo(s"${list}${x}") )
  }


  def entregarReporte(list:List[Drone])={
    val pw=new PrintWriter(new File(s"out${list.last.id}.txt"))
    println(list)
    pw.write("== Reporte de entregas ==")
    list.map(y=>pw.write(s"\n(${y.ubicacion.coordenada.x},${y.ubicacion.coordenada.y}) Orientacion ${y.ubicacion.orientacion.toString}"))
    pw.close()
  }
}

object InterpreteServicioArchivo extends InterpreteServicioArchivo


