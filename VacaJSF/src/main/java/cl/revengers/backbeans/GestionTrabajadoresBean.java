/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Trabajador;
import cl.revengers.sb.TrabajadorFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;


@Named(value = "gestionTrabajadoresBean")
@ViewScoped
public class GestionTrabajadoresBean implements Serializable {

    @EJB
    private TrabajadorFacadeLocal trabajadorFacade;

    private final static Logger logger = Logger.getLogger(GestionTrabajadoresBean.class);

    private List<Trabajador> listaTrabajadores;
    private Trabajador trabajadorSeleccionado;
    private String rutBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionTrabajadoresBean() {
    }

    @PostConstruct
    public void init() {
        this.listaTrabajadores = trabajadorFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Trabajador trab = (Trabajador) event.getObject();
            trabajadorFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Trabajador editado exitosamente.", ((Trabajador) event.getObject()).getIdTrabajador().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando trabajador.", e);
        }
    }

    public void eliminarTrabajador() {
        try {
            if (this.getListaTrabajadores() != null && !this.getListaTrabajadores().isEmpty() && this.getTrabajadorSeleccionado() != null) {
                this.getListaTrabajadores().remove(this.getTrabajadorSeleccionado());
                trabajadorFacade.remove(this.getTrabajadorSeleccionado());
                this.trabajadorSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Trabajador eliminado exitosamente.", "Trabajador eliminado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un Trabajador de la tabla.", "Debe seleccionar al menos un Trabajador de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando trabajador.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando Trabajador.", "Error grave eliminando Trabajador.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }

    public List<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public Trabajador getTrabajadorSeleccionado() {
        return trabajadorSeleccionado;
    }

    public void setTrabajadorSeleccionado(Trabajador trabajadorSeleccionado) {
        this.trabajadorSeleccionado = trabajadorSeleccionado;
    }

    public String getRutBusqueda() {
        return rutBusqueda;
    }

    public void setRutBusqueda(String rutBusqueda) {
        this.rutBusqueda = rutBusqueda;
    }

    public String getDvBusqueda() {
        return dvBusqueda;
    }

    public void setDvBusqueda(String dvBusqueda) {
        this.dvBusqueda = dvBusqueda;
    }
}
