/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Faena;
import cl.revengers.entities.Cliente;
import cl.revengers.sb.FaenaFacadeLocal;
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
 * @author Esteban Perez INNOVA-TI
 */
@Named(value = "crearFaenaBean")
@ViewScoped
public class CrearFaenaBean implements Serializable {

    @EJB
    private FaenaFacadeLocal trabajadorFacade;

    private final static Logger logger = Logger.getLogger(CrearFaenaBean.class);

    private String descripcion;
    private String ubicacion;
    private String estado;
    private Cliente idCliente;
    private int idF;

    private Date fechaInicio;

    private Date fechaTermino;

    public CrearFaenaBean() {
    }

    public void crearFaena() {
        Faena trab = null;
        try {
            if (this.getIdCliente() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un Cliente.", "Error: Debe seleccionar un cliente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            trab = new Faena();
            trab.setIdFaena(0);
            trab.setIdCliente(this.getIdCliente());
            trab.setUbicacion(this.getUbicacion().trim());
            trab.setEstado(this.getEstado().trim());
            trab.setDescripcion(this.getDescripcion().trim());

            trab.setFechIni(this.getFechaInicio());
            trab.setFechTer(this.getFechaTermino());

            trabajadorFacade.create(trab);
            this.limpiarValores();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "faena creada exitosamente.", "faena creada exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            logger.error("Error grave creando faena.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando faena.", "Error grave creando faena.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setDescripcion("");
            this.setUbicacion("");
            this.setEstado("");
            this.setIdCliente(null);

            this.setFechaInicio(null);

            this.setFechaTermino(null);

        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

}
