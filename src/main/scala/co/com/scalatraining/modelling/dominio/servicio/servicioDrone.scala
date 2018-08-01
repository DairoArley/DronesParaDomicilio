package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades._
import com.sun.net.httpserver.Authenticator.Success

import scala.concurrent.Future
import scala.util.{Success, Try}

sealed trait AlgebraServicioDrone {
  def moverDrone(instruccion: Instruccion, drone: Drone): Try[Drone]
  def girarIzquierda(drone: Drone): Ubicacion
  def girarDerecha(drone: Drone): Ubicacion
  def seguirAdelante(drone: Try[ Drone ]): Ubicacion
  def obstaculoEnMetros(x:Int, y:Int):Try[Drone]
  def llevarAlmuerzo(entrega: Entrega, drone: Drone):Drone
  def llevarPedido(pedido: Pedido, drone: Drone):List[Reporte]
  def tomarPedido(list: List[String]):Try[Pedido]

}
sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

  override def moverDrone( instruccion: Instruccion, drone: Drone ): Try[Drone] = {

    val ubicacion = instruccion match {
      case A() => seguirAdelante(Try(drone) )
      case I() => girarIzquierda( drone)
      case D() => girarDerecha( drone)
    }

    //A la redonda
    ubicacion match {
      case Success(nam) : Try(Drone() )
    }

    //Drone(ubicacion, drone.capacidad,drone.id)
    val rango = Try{ Drone(ubicacion, drone.capacidad,drone.id) }


  }

  override def girarIzquierda(drone: Drone): Ubicacion = {
    val coordenada = drone.ubicacion.coordenada
    val retorno = drone.ubicacion.orientacion match {
      case Norte('N') => Ubicacion(coordenada, Oeste('O') )
      case Sur('S') => Ubicacion(coordenada, Este('E'))
      case Oeste('S') => Ubicacion(coordenada, Sur('S'))
      case Este('E') => Ubicacion(coordenada, Norte('N') )
    }
    retorno
  }

  override def girarDerecha(drone: Drone): Ubicacion = {
    val coordenada = drone.ubicacion.coordenada
    val retorno = drone.ubicacion.orientacion match {
      case Norte('N') => Ubicacion(coordenada, Este('E'))
      case Sur('S') => Ubicacion(coordenada, Oeste('O'))
      case Oeste('O') => Ubicacion(coordenada, Norte('N'))
      case Este('E') => Ubicacion(coordenada, Sur('S'))
    }
    retorno
  }

  override def seguirAdelante(drone:Try[ Drone ]): Ubicacion = {
    val coordenada = drone.get.ubicacion.coordenada
    val orientacion = drone.get.ubicacion.orientacion
    val retorno = drone.get.ubicacion.orientacion match {
      case Norte(char) => if( )else

        Ubicacion(Coordenada(coordenada.x, coordenada.y+1), orientacion)
      case Sur(char) => Ubicacion(Coordenada(coordenada.x, coordenada.y-1), orientacion)
      case Oeste(char) => Ubicacion(Coordenada(coordenada.x-1, coordenada.y), orientacion)
      case Este(char) => Ubicacion(Coordenada(coordenada.x+1, coordenada.y), orientacion)
    }
    retorno
  }

  override def llevarAlmuerzo(entrega: Entrega, drone: Drone): Drone = {
    val recorrido = List(drone)
    val trazo = entrega.list.foldLeft(recorrido){(resultado, item) => resultado :+ moverDrone(item, resultado.last)}
    trazo.last

    //Reporte(trazo.last.ubicacion.coordenada.x, trazo.last.ubicacion.coordenada.x, trazo.last.ubicacion.orientacion)
  }

  override def llevarPedido(pedido: Pedido, drone: Drone): List[Reporte] = {

    val recorrido = List(drone)
    val trazo = pedido.list.foldLeft(recorrido){(resultado, item) => resultado :+ llevarAlmuerzo(item, resultado.last)}
    trazo.last

    val listaReporte = trazo.tail.map( x => Reporte(x.ubicacion.coordenada.x, x.ubicacion.coordenada.y, x.ubicacion.orientacion))
    listaReporte
  }

  override def tomarPedido(list: List[String]): Try[Pedido] = {
    val pedido = list.map( x => x.toList).map( x => x.map( y => Instruccion.newInstruccion(y)) ).map( x => Entrega(x))
    if ( Try{Pedido(pedido)}.isSuccess == Try() )

    Try(Pedido(pedido))

    //-------------One for One
    /*val pedido1 = list.map(x => x.toList)

    val pedido2 = pedido1.map( x => x.map( y => Instruccion.newInstruccion(y)))

    val pedido3 = pedido2.map(x => Entrega(x))

    val pedido4 = Pedido(pedido3)

    pedido4
    */

    //-------------
  }

}

object InterpretacionServicioDrone extends InterpretacionServicioDrone