/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Trabajador;
import cl.revengers.sb.TrabajadorFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * 
 */
@Named(value = "ingresoTrabajadorBean")
@ViewScoped
public class IngresoTrabajadorBean implements Serializable {

    @EJB
    private TrabajadorFacadeLocal trabajadorFacade;

    private final static Logger logger = Logger.getLogger(IngresoTrabajadorBean.class);

    private String nombreTrabajador;
    private String apellidoPTrabajador;
    private String apellidoMTrabajador;
    private int rutTrabajador;
    private String telefonoTrabajador;
    private String direccionTrabajador;
    private Date fechaNacimientoTrabajador;
    private String afpTrabajador;
    private String estadoCivilTrabajador;

    public IngresoTrabajadorBean() {
    }

    public void agregarTrabajador() {
        Trabajador trab = null;
        try {
            
            
           try{ 
            if (Integer.parseInt(this.getTelefonoTrabajador())<0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Numero de telefono incorrecto", "Error: Numero de telefono incorrecto.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
                
            }
            if (this.getRutTrabajador()>999999999) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Rut incorrecto", "Error: Numero de telefono incorrecto.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
                
            }
            if (this.getRutTrabajador()<1000000) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Rut incorrecto", "Error: Numero de telefono incorrecto.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
                
            }
           } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }

            trab = trabajadorFacade.getTrabajadorByRut(this.getRutTrabajador());

            if (trab == null) {
                trab = new Trabajador();
                trab.setIdTrabajador(0);
                trab.setRut(this.getRutTrabajador());
                trab.setNombre(this.getNombreTrabajador().trim());
                trab.setApellidoP(this.getApellidoPTrabajador().trim());
                trab.setApellidoM(this.getApellidoMTrabajador().trim());
                trab.setDireccion(this.getDireccionTrabajador().trim());
                trab.setFechNac(this.getFechaNacimientoTrabajador());
                trab.setPrevision(this.getAfpTrabajador().trim());
                trab.setECivil(this.getEstadoCivilTrabajador().trim());
                trab.setTelefono(this.getTelefonoTrabajador().trim());
                trabajadorFacade.create(trab);
                this.limpiarValores();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Trabajador creado exitosamente.", "Trabajador creado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, trabajador ya existe.", "Error, trabajador ya existe en base de datos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            logger.error("Error grave creando trabajador.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, Es posible que alguno de sus campos este incorrecto.", "Error, Es posible que alguno de sus campos este incorrecto.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setNombreTrabajador("");
            this.setApellidoPTrabajador("");
            this.setApellidoMTrabajador("");
            this.setRutTrabajador(0);
            this.setTelefonoTrabajador("");
            this.setDireccionTrabajador("");
            this.setFechaNacimientoTrabajador(null);
            this.setAfpTrabajador("");
            this.setEstadoCivilTrabajador("");
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidoPTrabajador() {
        return apellidoPTrabajador;
    }

    public void setApellidoPTrabajador(String apellidoPTrabajador) {
        this.apellidoPTrabajador = apellidoPTrabajador;
    }

    public String getApellidoMTrabajador() {
        return apellidoMTrabajador;
    }

    public void setApellidoMTrabajador(String apellidoMTrabajador) {
        this.apellidoMTrabajador = apellidoMTrabajador;
    }

    public int getRutTrabajador() {
        return rutTrabajador;
    }

    public void setRutTrabajador(int rutTrabajador) {
        this.rutTrabajador = rutTrabajador;
    }

    public String getTelefonoTrabajador() {
        return telefonoTrabajador;
    }

    public void setTelefonoTrabajador(String telefonoTrabajador) {
        this.telefonoTrabajador = telefonoTrabajador;
    }

    public String getDireccionTrabajador() {
        return direccionTrabajador;
    }

    public void setDireccionTrabajador(String direccionTrabajador) {
        this.direccionTrabajador = direccionTrabajador;
    }

    public Date getFechaNacimientoTrabajador() {
        return fechaNacimientoTrabajador;
    }

    public void setFechaNacimientoTrabajador(Date fechaNacimientoTrabajador) {
        this.fechaNacimientoTrabajador = fechaNacimientoTrabajador;
    }

    public String getAfpTrabajador() {
        return afpTrabajador;
    }

    public void setAfpTrabajador(String afpTrabajador) {
        this.afpTrabajador = afpTrabajador;
    }

    public String getEstadoCivilTrabajador() {
        return estadoCivilTrabajador;
    }

    public void setEstadoCivilTrabajador(String estadoCivilTrabajador) {
        this.estadoCivilTrabajador = estadoCivilTrabajador;
    }
}
