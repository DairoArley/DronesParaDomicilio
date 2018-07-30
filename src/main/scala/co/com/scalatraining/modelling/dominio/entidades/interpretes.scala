package co.com.scalatraining.modelling.dominio.entidades

class interpretes {

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
      instruccion match {
        case A() => val hola = for {

        } yield
        case I() =>
        case D() =>_
      }
    }

    override def girarIzquierda(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = ???

    override def girarDerecha(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = ???

    override def seguirAdelante(ubicacionActual: Ubicacion, instruccion: Instruccion): Ubicacion = ???

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
