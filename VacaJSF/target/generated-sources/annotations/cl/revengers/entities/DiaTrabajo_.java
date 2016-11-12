package cl.revengers.entities;

import cl.revengers.entities.Faena;
import cl.revengers.entities.ResumenTrabajo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-11T19:48:43")
@StaticMetamodel(DiaTrabajo.class)
public class DiaTrabajo_ { 

    public static volatile SingularAttribute<DiaTrabajo, Integer> costoSupervision;
    public static volatile SingularAttribute<DiaTrabajo, Integer> idDia;
    public static volatile SingularAttribute<DiaTrabajo, Integer> costoTransporte;
    public static volatile SingularAttribute<DiaTrabajo, Faena> idFaena;
    public static volatile ListAttribute<DiaTrabajo, ResumenTrabajo> resumenTrabajoList;
    public static volatile SingularAttribute<DiaTrabajo, Date> fechaD;

}