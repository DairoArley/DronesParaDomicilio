package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.internal.Trees
import scala.util.{Failure, Success, Try}

sealed trait AlgebraServicioDrone {
  def moverDrone(instruccion: Instruccion, drone: Try[Drone]): Try [Drone]
  def girarIzquierda(drone: Try [ Drone ]): Try[Ubicacion]
  def girarDerecha(drone: Try [ Drone ]): Try[Ubicacion]
  def seguirAdelante(drone: Try[ Drone ]): Try[Ubicacion]
  def pocisionEsValida(drone: Drone, ubicacion: Ubicacion):Try[Ubicacion]
  def llevarEntrega(entrega: Entrega, drone:Try[ Drone ]):Try[Drone]
  def llevarPedido(pedido: Pedido, drone: Drone):List[Reporte]
  def tomarPedido(list: List[String]):Pedido
  def mandarDrones(listaDrones: List[Drone], listaPedidos :List[Pedido])
  def tomarPedidos(list: List[List[String]]): List[Pedido]
  def reportarInformacion(drone: Drone)

}
sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

  override def moverDrone( instruccion: Instruccion, drone: Try[Drone] ): Try[Drone] = {
    //val drone1 = drone.getOrElse(return Failure{Drone})
    val ubicacion = instruccion match {
      case A() => seguirAdelante(drone )
      case I() => girarIzquierda( drone )
      case D() => girarDerecha( drone )
    }
    //Asumiendo que un objeto compuesto de atributo Failure es un Failure

    Try { Drone(ubicacion.get, drone.get.capacidad, drone.get.id) }
  }

  override def girarIzquierda(drone:Try[ Drone ]): Try[Ubicacion] = {
    val coordenada = drone.get.ubicacion.coordenada
    //val orientacion1 = drone.getOrElse()
    drone.get.ubicacion.orientacion match {
      case Norte('N') => Try {
        Ubicacion(coordenada, Oeste('O'))
      }
      case Sur('S') => Try {
        Ubicacion(coordenada, Este('E'))
      }
      case Oeste('S') => Try {
        Ubicacion(coordenada, Sur('S'))
      }
      case Este('E') => Try {
        Ubicacion(coordenada, Norte('N'))
      }
    }
  }

  override def girarDerecha(drone: Try[Drone]): Try[Ubicacion ]= {
    val coordenada = drone.get.ubicacion.coordenada
    drone.get.ubicacion.orientacion match {
      case Norte('N') => Try {
        Ubicacion(coordenada, Este('E'))
      }
      case Sur('S') => Try {
        Ubicacion(coordenada, Oeste('O'))
      }
      case Oeste('O') => Try {
        Ubicacion(coordenada, Norte('N'))
      }
      case Este('E') => Try {
        Ubicacion(coordenada, Sur('S'))
      }
    }
  }

  override def seguirAdelante(drone:Try[ Drone ]): Try[Ubicacion] = {
    val coordenada = drone.get.ubicacion.coordenada
    val orientacion = drone.get.ubicacion.orientacion
    drone.get.ubicacion.orientacion match {
      case Norte(char) => pocisionEsValida(drone.get, Ubicacion( Coordenada(coordenada.x, coordenada.y+1), orientacion))
      case Sur(char) => pocisionEsValida(drone.get, Ubicacion( Coordenada(coordenada.x, coordenada.y-1), orientacion))
      case Oeste(char) => pocisionEsValida(drone.get, Ubicacion( Coordenada(coordenada.x-1, coordenada.y), orientacion))
      case Este(char) => pocisionEsValida(drone.get, Ubicacion( Coordenada(coordenada.x+1, coordenada.y), orientacion))
    }
  }

//---------- Regla de negocio
  override def pocisionEsValida(drone: Drone, ubicacion: Ubicacion):Try[Ubicacion] = {
    if (Math.sqrt(  Math.pow(ubicacion.coordenada.x,2) + Math.pow(ubicacion.coordenada.y,2) ) <= 10 )
      Try{ubicacion}
    else Try(throw new Exception("Se salio de rango"))
  }

  override def llevarEntrega(entrega: Entrega, drone:Try[ Drone ]):Try[Drone]= {
    val recorrido = List(drone )
    val trazo = entrega.list.foldLeft(recorrido){(resultado, item) => resultado :+ moverDrone(item, resultado.last)}
    reportarInformacion(trazo.last.get)
    val reporte = trazo.map( x => x.get)
    InterpreteServicioArchivo.entregarReporte(reporte.tail)
    Try{trazo.last.get}

    //Reporte(trazo.last.ubicacion.coordenada.x, trazo.last.ubicacion.coordenada.x, trazo.last.ubicacion.orientacion)
  }

  //def asincrono(pedido: Pedido, drone: Drone)(implicit ec: ExecutionContext): Future[List[Drone]] = Future(llevarPedido(drone, pedido))

  override def reportarInformacion(drone: Drone): Unit = {
    val hilera = drone.ubicacion.orientacion
    print(s"${drone.ubicacion} direccion ")
    if(hilera == Norte('N')) println("Norte")else if
      (hilera == Sur('S')) println("Sur")else if
      (hilera == Oeste('O')) print("Oeste") else
      println("Este")
  }

  override def llevarPedido(pedido: Pedido, drone: Drone): List[Reporte] = {
    val recorrido = List(Try{drone})
    val trazo = pedido.list.foldLeft(recorrido){(resultado, item) => resultado :+ llevarEntrega(item, resultado.last)}
    val listaReporte = trazo.tail.map( x => Reporte(x.get.ubicacion.coordenada.x, x.get.ubicacion.coordenada.y, x.get.ubicacion.orientacion))
    listaReporte
  }

  override def tomarPedido(list: List[String]): Pedido = {
    val pedido = list.map(x =>  x.toList ).map(y => y.map(z => Instruccion.newInstruccion(z) )  ).map( y => Entrega(y))
    Pedido(pedido)
  }

  override def tomarPedidos(list: List[List[String]]): List[Pedido] = {
    val listaPedidos = list.map( x => tomarPedido(x) )
    listaPedidos
  }

  override def mandarDrones(listaDrones: List[Drone], listaPedidos :List[Pedido]): Unit = {
    val todo = listaDrones.zip( listaPedidos )
    val nada = todo.map( x => llevarPedido(x._2, x._1))
  }

}

object InterpretacionServicioDrone extends InterpretacionServicioDrone