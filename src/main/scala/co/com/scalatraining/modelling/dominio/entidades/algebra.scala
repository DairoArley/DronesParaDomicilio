package co.com.scalatraining.modelling.dominio.entidades

// algbra

sealed trait Ubicacion {
  def actualizarUbicacion(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
}

sealed trait AlgebraServiciosDrone {
  def mover(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
  def girarIzquierda(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
  def girarDerecha(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
  def seguirAdelante(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion
}



