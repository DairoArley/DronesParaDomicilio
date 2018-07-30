package co.com.scalatraining.modelling.dominio.entidades

// algbra

sealed trait Ubicacion {
  def actualizarUbicacion(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
  def obtenerCoordenada():Coordenada
  def obtenerOrientacion():Int
}
