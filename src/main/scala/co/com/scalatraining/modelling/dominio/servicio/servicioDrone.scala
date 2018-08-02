package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

sealed trait AlgebraServicioDrone {
  def moverDrone(instruccion: Instruccion, drone: Drone): Drone
  def girarIzquierda(drone: Try [ Drone ]): Try[Ubicacion]
  def girarDerecha(drone: Try [ Drone ]): Try[Ubicacion]
  def seguirAdelante(drone: Try[ Drone ]): Try[Ubicacion]
  def pocisionEsValida(x:Int, y:Int):Boolean
  def llevarAlmuerzo(entrega: Entrega, drone: Drone):Drone
  def llevarPedido(pedido: Pedido, drone: Drone):List[Reporte]
  def tomarPedido(list: List[String]):Try[Pedido]

}
sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

  override def moverDrone( instruccion: Instruccion, drone: Drone ): Drone = {
    val ubicacion = instruccion match {
      case A() => seguirAdelante(Try(drone) )
      case I() => girarIzquierda( Try (drone) )
      case D() => girarDerecha( Try (drone) )
    }
    //A la redonda
    ubicacion match {
      case Success(nam) => Drone(ubicacion.get, drone.capacidad, drone.id)
      case _ => Drone(Ubicacion(Coordenada(0, 0), Norte('N')), drone.capacidad, drone.id)
    }
  }

  override def girarIzquierda(drone:Try[ Drone ]): Try[Ubicacion] = {
    val coordenada = drone.get.ubicacion.coordenada
    val retorno = drone.get.ubicacion.orientacion match {
      case Norte('N') => Try {Ubicacion(coordenada, Oeste('O') )}
      case Sur('S') => Try {Ubicacion(coordenada, Este('E'))}
      case Oeste('S') => Try{ Ubicacion(coordenada, Sur('S')) }
      case Este('E') => Try{ Ubicacion(coordenada, Norte('N') ) }
    }
    retorno
  }

  override def girarDerecha(drone: Try[Drone]): Try[Ubicacion ]= {
    val coordenada = drone.get.ubicacion.coordenada
    val retorno = drone.get.ubicacion.orientacion match {
      case Norte('N') => Try{ Ubicacion(coordenada, Este('E'))}
      case Sur('S') => Try{ Ubicacion(coordenada, Oeste('O')) }
      case Oeste('O') => Try{ Ubicacion(coordenada, Norte('N')) }
      case Este('E') => Try{ Ubicacion(coordenada, Sur('S')) }
    }
    retorno
  }

  override def seguirAdelante(drone:Try[ Drone ]): Try[Ubicacion] = {
    val coordenada = drone.get.ubicacion.coordenada
    val orientacion = drone.get.ubicacion.orientacion
    drone.get.ubicacion.orientacion match {
      case Norte(char) =>  if( !pocisionEsValida(coordenada.x, coordenada.y+1) ) Try(throw new Exception("Paila"))else
        Try{ Ubicacion(Coordenada(coordenada.x, coordenada.y+1), orientacion) }
      case Sur(char) => if( !pocisionEsValida(coordenada.x, coordenada.y-1) ) Try(throw new Exception("Paila"))else
        Try{ Ubicacion(Coordenada(coordenada.x, coordenada.y-1), orientacion) }
      case Oeste(char) => if( !pocisionEsValida(coordenada.x-1, coordenada.y) ) Try(throw new Exception("Paila"))else
        Try{ Ubicacion(Coordenada(coordenada.x-1, coordenada.y), orientacion) }
      case Este(char) => if( !pocisionEsValida(coordenada.x+1, coordenada.y) ) Try(throw new Exception("Paila"))else
        Try{ Ubicacion(Coordenada(coordenada.x+1, coordenada.y), orientacion)}
    }
  }

//---------- Regla de negocio
  override def pocisionEsValida(x: Int, y: Int):Boolean = {
    Math.sqrt(  Math.pow(x,2) + Math.pow(y,2) ) <= 10
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