package cl.revengers.entities;

import cl.revengers.entities.Producto;
import cl.revengers.entities.ResumenTrabajo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-12T23:49:18")
@StaticMetamodel(ResumenProductos.class)
public class ResumenProductos_ { 

    public static volatile SingularAttribute<ResumenProductos, Integer> idResProd;
    public static volatile SingularAttribute<ResumenProductos, Producto> idProd;
    public static volatile SingularAttribute<ResumenProductos, Integer> cantidad;
    public static volatile SingularAttribute<ResumenProductos, ResumenTrabajo> idResumen;

}