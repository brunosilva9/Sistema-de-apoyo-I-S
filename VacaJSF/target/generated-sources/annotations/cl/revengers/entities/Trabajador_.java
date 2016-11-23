package cl.revengers.entities;

import cl.revengers.entities.Adelanto;
import cl.revengers.entities.Prestamo;
import cl.revengers.entities.ResumenTrabajo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-23T00:34:37")
@StaticMetamodel(Trabajador.class)
public class Trabajador_ { 

    public static volatile SingularAttribute<Trabajador, String> apellidoP;
    public static volatile SingularAttribute<Trabajador, String> direccion;
    public static volatile SingularAttribute<Trabajador, Integer> idTrabajador;
    public static volatile SingularAttribute<Trabajador, String> nombre;
    public static volatile SingularAttribute<Trabajador, String> prevision;
    public static volatile SingularAttribute<Trabajador, Integer> rut;
    public static volatile ListAttribute<Trabajador, Prestamo> prestamoList;
    public static volatile SingularAttribute<Trabajador, Date> fechNac;
    public static volatile SingularAttribute<Trabajador, String> eCivil;
    public static volatile ListAttribute<Trabajador, ResumenTrabajo> resumenTrabajoList;
    public static volatile SingularAttribute<Trabajador, String> apellidoM;
    public static volatile SingularAttribute<Trabajador, String> telefono;
    public static volatile ListAttribute<Trabajador, Adelanto> adelantoList;

}