/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Implemento;
import cl.revengers.entities.Prestamo;
import cl.revengers.sb.ImplementoFacadeLocal;
import cl.revengers.sb.PrestamoFacadeLocal;
import cl.revengers.entities.Trabajador;
import cl.revengers.sb.TrabajadorFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Named(value = "prestamosImpBean")
@ViewScoped
public class PrestamosImpBean implements Serializable {

    @EJB
    private ImplementoFacadeLocal impFacade;
    @EJB
    private TrabajadorFacadeLocal trabFacade;
    @EJB
    private PrestamoFacadeLocal prestamoFacade;
    private final static Logger logger = Logger.getLogger(GestionClientesBean.class);

    private List<Implemento> listaImp;
    private Implemento impSeleccionado;
    private List<Trabajador> listaTrab;
    private List<Trabajador> listaTrabSeleccionados;
    private Trabajador trabSeleccionado;
    private Date fechaPrestamo;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public PrestamosImpBean() {
    }

    @PostConstruct
    public void init() {
        this.listaImp = impFacade.findAll();
        this.listaTrab = trabFacade.findAll();
    }

   

     public void crearPrestamo() {
        Prestamo trab = null;
        
        try {

            if (this.getImpSeleccionado() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un producto.", "Error: Debe seleccionar un producto.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            if (this.getFechaPrestamo() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un dia.", "Error: Debe seleccionar un dia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            if (this.listaTrabSeleccionados== null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un Trabajador.", "Error: Debe seleccionar un Trabajador.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            
            for (Trabajador trabajador : this.getListaTrabSeleccionados()) {
            trab = new Prestamo();
            trab.setIdPrestamo(0);
            trab.setIdTraba(trabajador);
            trab.setIdImp(impSeleccionado);
            trab.setFechaPrestamo(fechaPrestamo);
           
            prestamoFacade.create(trab);
            }
            this.limpiarValores();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resumen trabajo creado exitosamente.", "Resumen trabajo creada exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            logger.error("Error grave creando resumen trabajo.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando resumen trabajo .", "Error grave creando resumen trabajo.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
       public void limpiarValores() {
        try {
            this.setFechaPrestamo(null);
            this.setImpSeleccionado(null);
            this.setTrabSeleccionado(null);
           
            
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public List<Implemento> getListaImp() {
        return listaImp;
    }

    public void setListaImp(List<Implemento> listaImp) {
        this.listaImp = listaImp;
    }

    public Implemento getImpSeleccionado() {
        return impSeleccionado;
    }

    public void setImpSeleccionado(Implemento impSeleccionado) {
        this.impSeleccionado = impSeleccionado;
    }

    public Trabajador getTrabSeleccionado() {
        return trabSeleccionado;
    }

    public void setTrabSeleccionado(Trabajador trabSeleccionado) {
        this.trabSeleccionado = trabSeleccionado;
    }

    public List<Trabajador> getListaTrab() {
        return listaTrab;
    }

    public void setListaTrab(List<Trabajador> listaTrab) {
        this.listaTrab = listaTrab;
    }

    public List<Trabajador> getListaTrabSeleccionados() {
        return listaTrabSeleccionados;
    }

    public void setListaTrabSeleccionados(List<Trabajador> listaTrabSeleccionados) {
        this.listaTrabSeleccionados = listaTrabSeleccionados;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    
    

    
}
