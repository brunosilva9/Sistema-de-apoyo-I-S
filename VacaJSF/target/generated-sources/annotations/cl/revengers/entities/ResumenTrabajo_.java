package cl.revengers.entities;

import cl.revengers.entities.DiaTrabajo;
import cl.revengers.entities.ResumenProductos;
import cl.revengers.entities.Trabajador;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-16T01:30:25")
@StaticMetamodel(ResumenTrabajo.class)
public class ResumenTrabajo_ { 

    public static volatile SingularAttribute<ResumenTrabajo, String> estado;
    public static volatile ListAttribute<ResumenTrabajo, ResumenProductos> resumenProductosList;
    public static volatile SingularAttribute<ResumenTrabajo, DiaTrabajo> idDia;
    public static volatile SingularAttribute<ResumenTrabajo, Trabajador> idTrabajador;
    public static volatile SingularAttribute<ResumenTrabajo, Integer> idResumen;

}