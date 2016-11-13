package cl.revengers.entities;

import cl.revengers.entities.Trabajador;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-13T02:51:49")
@StaticMetamodel(Adelanto.class)
public class Adelanto_ { 

    public static volatile SingularAttribute<Adelanto, Integer> idAdelanto;
    public static volatile SingularAttribute<Adelanto, Integer> monto;
    public static volatile SingularAttribute<Adelanto, Boolean> descontado;
    public static volatile SingularAttribute<Adelanto, Trabajador> idTrab;

}