package co.com.scalatraining.modelling.dominio.entidades

class interpretes {

  sealed trait ServiciosUbicacion extends AlgebraUbicacion{
    override def actualizarUbicacion(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = {
      instruccion match {
        case A() => {for ()}
        case I() =>
        case D() =>
        case _ =>
      }
    }
  }

  object Instruccion {
    def newInstruccion(c: Char): Instruccion = {
      c match {
        case 'A' => A()
        case 'D' => D()
        case 'I' => I()
        case _ => throw new Exception(s"Caracter invalido para creacion de instruccion: $c")
      }
    }
  }

  sealed trait InterpretacionServiciosDrone extends AlgebraServiciosDrone {
    override def mover(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = {
      /*val hola = for {
        x <- instruccion
      }yield{}*/


      instruccion match {
        case A() => seguirAdelante(ubicacionActual)
        case I() =>
        case D() =>_
      }
    }

    override def girarIzquierda(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = {
      val aux = ubicacionActual.coordenada
      val retorno = ubicacionActual.orientacion match {
        case Norte(char) => Ubicacion(Coordenada(aux.x, aux.y + 1), ubicacionActual.orientacion)
        case Sur(char) => Ubicacion(Coordenada(aux.x, aux.y - 1), ubicacionActual.orientacion)
        case Oeste(char) => Ubicacion(Coordenada(aux.x - 1, aux.y), ubicacionActual.orientacion)
        case Este(char) => Ubicacion(Coordenada(aux.x + 1, aux.y), ubicacionActual.orientacion)
      }
      retorno
    }

    override def girarDerecha(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = ???

    override def seguirAdelante(ubicacionActual: Ubicacion): Ubicacion = {
      val aux = ubicacionActual.coordenada
      val retorno = ubicacionActual.orientacion match {
        case Norte(char) => Ubicacion(Coordenada(aux.x, aux.y+1), ubicacionActual.orientacion)
        case Sur(char) => Ubicacion(Coordenada(aux.x, aux.y-1), ubicacionActual.orientacion)
        case Oeste(char) => Ubicacion(Coordenada(aux.x-1, aux.y), ubicacionActual.orientacion)
        case Este(char) => Ubicacion(Coordenada(aux.x+1, aux.y), ubicacionActual.orientacion)
      }
      retorno
    }

  }

  sealed trait AlgebraEntrega {
    def apply(string: String): Option[AlgebraEntrega]
  }

  sealed trait InterpreteEntrega extends AlgebraEntrega {
    /*val direccion = string.toList
      direccion match {
        case string => Option(Direccion(string))
        case Option()
      }  */


  }

}
