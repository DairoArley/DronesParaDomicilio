package co.com.scalatraining.modelling.dominio.entidades

import scala.concurrent.Future

class entidades {

  object orientacion extends Orientacion

  sealed trait AlgebraEntrega{
    def apply(string: String): Option[AlgebraEntrega]
  }
  sealed trait InterpreteEntrega extends AlgebraEntrega{
    override def apply(string:String): Option[AlgebraEntrega] = {
      val direccion = string.toList
      direccion match {
        case string => Option (Direccion(string))
        case  Option ()
      }
    }
  }

  case class Drone(){
    def apply(ubicacion: Ubicacion): Drone = new Drone(ubicacion., ubicacion.obtenerorientacion())

  }

  sealed trait AlgebraDrone{
    def mover(ubicacionActual: Ubicacion, instruccion: Instruccion):Future[Ubicacion]
    def girarIzquierda(ubicacionActual: Ubicacion, instruccion: Instruccion):Future[Ubicacion]
    def girarDerecha(ubicacionActual: Ubicacion, instruccion: Instruccion):Future[Ubicacion]
    def seguirAdelante(ubicacionActual: Ubicacion, instruccion: Instruccion):Future[Ubicacion]
  }

  sealed trait InterpretacionDrone extends AlgebraDrone{
    override def mover(ubicacionActual: Ubicacion, instruccion: Instruccion): Future[Ubicacion] = {
      instruccion match{
        case A() => val hola = for(x <- ubicacionActual.) yield
        case_
      }
    }
    override def girarIzquierda(ubicacionActual: Ubicacion, instruccion: Instruccion): Future[Ubicacion] = {

    }
    override def girarDerecha(ubicacionActual: Ubicacion, instruccion: Instruccion): Future[Ubicacion] = ???
    override def seguirAdelante(ubicacionActual: Ubicacion, instruccion: Instruccion): Future[Ubicacion] = ???

  }
  object drone extends InterpretacionDrone
  object interpreteEntrega extends InterpreteEntrega
  interpreteEntrega.apply()

  //---------------------
  sealed trait AlgebraServicioPoliza {
    def tarifar(poliza:Poliza):Future[Tarifa]
    def consultarPoliza(numero:String):Future[Poliza]
    def adicionarCobertura(cobertura:Cobertura, poliza:Poliza): Future[Poliza]
    def crearCobertura(cdgarantia: String, cdsubgarantia:String): Future[Cobertura]
  }

  //Interpretacion del algebra
  sealed trait InterpretacionServicioPoliza extends AlgebraServicioPoliza{

    override def tarifar(poliza:Poliza):Future[Tarifa] = {
      Future.successful(Tarifa(1000))
    }

    override def consultarPoliza(numero:String):Future[Poliza] = {
      Future(Poliza(numero, List(Cobertura("1", "1.1"))))
    }

    override def adicionarCobertura(cobertura:Cobertura, poliza:Poliza): Future[Poliza] = {
      Future{
        Poliza(poliza.numero, cobertura::poliza.coberturas)
      }
    }

    override def crearCobertura(cdgarantia: String, cdsubgarantia:String): Future[Cobertura] = Future{
      Cobertura(cdgarantia, cdsubgarantia)
    }
  }

  // Trait Object
  object InterpretacionServicioPoliza extends InterpretacionServicioPoliza


}
