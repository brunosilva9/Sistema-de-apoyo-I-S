package cl.revengers.entities;

import cl.revengers.entities.Implemento;
import cl.revengers.entities.Trabajador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-11T19:48:43")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Trabajador> idTraba;
    public static volatile SingularAttribute<Prestamo, Date> fechaPrestamo;
    public static volatile SingularAttribute<Prestamo, Implemento> idImp;
    public static volatile SingularAttribute<Prestamo, Integer> idPrestamo;

}