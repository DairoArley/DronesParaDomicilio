package co.com.scalatraining.modelling.dominio.servicio

import co.com.scalatraining.modelling.dominio.entidades._

sealed trait AlgebraServicioDrone {
  def moverDrone(instruccion: Instruccion, drone: Drone): Drone
  def girarIzquierda(drone: Drone): Ubicacion
  def girarDerecha(drone: Drone): Ubicacion
  def seguirAdelante(drone: Drone): Ubicacion
  def llevarAlmuerzo(ruta: Ruta, drone: Drone):Reporte
  def llevarPedido(pedido: Pedido, drone: Drone):List[Reporte]
}

sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

  override def moverDrone( instruccion: Instruccion, drone: Drone ): Drone = {

    val res= instruccion match {
      case A() => seguirAdelante(drone)
      case I() => girarIzquierda(drone)
      case D() => girarDerecha(drone)
    }
    Drone(res, drone.capacidad,drone.id)
  }

  override def girarIzquierda(drone: Drone): Ubicacion = {
    val aux = drone.ubicacion.coordenada
    val retorno = drone.ubicacion.orientacion match {
      case Norte(char) => Ubicacion(Coordenada(aux.x, aux.y), Oeste(char) )
      case Sur(char) => Ubicacion(Coordenada(aux.x, aux.y ), Este(char))
      case Oeste(char) => Ubicacion(Coordenada(aux.x , aux.y), Sur(char))
      case Este(char) => Ubicacion(Coordenada(aux.x , aux.y), Norte(char) )
    }
    retorno
  }

  override def girarDerecha(drone: Drone): Ubicacion = {
    val aux = drone.ubicacion.coordenada
    val retorno = drone.ubicacion.orientacion match {
      case Norte(char) => Ubicacion(Coordenada(aux.x, aux.y ), Este(char))
      case Sur(char) => Ubicacion(Coordenada(aux.x, aux.y ), Oeste(char))
      case Oeste(char) => Ubicacion(Coordenada(aux.x , aux.y), Norte(char))
      case Este(char) => Ubicacion(Coordenada(aux.x , aux.y), Sur(char))
    }
    retorno
  }

  override def seguirAdelante(drone: Drone): Ubicacion = {
    val aux = drone.ubicacion.coordenada
    val retorno = drone.ubicacion.orientacion match {
      case Norte(char) => Ubicacion(Coordenada(aux.x, aux.y+1), drone.ubicacion.orientacion)
      case Sur(char) => Ubicacion(Coordenada(aux.x, aux.y-1), drone.ubicacion.orientacion)
      case Oeste(char) => Ubicacion(Coordenada(aux.x-1, aux.y), drone.ubicacion.orientacion)
      case Este(char) => Ubicacion(Coordenada(aux.x+1, aux.y), drone.ubicacion.orientacion)
    }
    retorno
  }

  override def llevarAlmuerzo(ruta: Ruta, drone: Drone): Reporte = {
    val recorrido = List(drone)
    val liposAct = ruta.list.foldLeft(recorrido){(resultado, item) => resultado :+ moverDrone(item, resultado.last)}

    Reporte(recorrido.last.ubicacion.coordenada.x, recorrido.last.ubicacion.coordenada.x, recorrido.last.ubicacion.orientacion)
  }

  override def llevarPedido(pedido: Pedido, drone: Drone): List[Reporte] = {
    pedido.list.foreach(x => llevarAlmuerzo(x, drone) )
    val list:List[Reporte] = pedido.list.map( x => llevarAlmuerzo(x,drone))
    list
  }

}

object InterpretacionServicioDrone extends InterpretacionServicioDrone