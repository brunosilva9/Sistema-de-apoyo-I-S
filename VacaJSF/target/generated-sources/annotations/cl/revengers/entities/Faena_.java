package cl.revengers.entities;

import cl.revengers.entities.Cliente;
import cl.revengers.entities.DiaTrabajo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-16T01:43:17")
@StaticMetamodel(Faena.class)
public class Faena_ { 

    public static volatile SingularAttribute<Faena, String> descripcion;
    public static volatile SingularAttribute<Faena, String> ubicacion;
    public static volatile SingularAttribute<Faena, String> estado;
    public static volatile SingularAttribute<Faena, Cliente> idCliente;
    public static volatile SingularAttribute<Faena, Date> fechTer;
    public static volatile ListAttribute<Faena, DiaTrabajo> diaTrabajoList;
    public static volatile SingularAttribute<Faena, Integer> idFaena;
    public static volatile SingularAttribute<Faena, Date> fechIni;

}