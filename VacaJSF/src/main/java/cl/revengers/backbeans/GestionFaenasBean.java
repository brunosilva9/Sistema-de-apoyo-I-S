/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Faena;
import cl.revengers.entities.Trabajador;
import cl.revengers.sb.FaenaFacadeLocal;
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


@Named(value = "gestionFaenasBean")
@ViewScoped
public class GestionFaenasBean implements Serializable {

    @EJB
    private FaenaFacadeLocal faenaFacade;
    @EJB
    private TrabajadorFacadeLocal trabFacade;

    private final static Logger logger = Logger.getLogger(GestionTrabajadoresBean.class);

    private List<Faena> listaFaenas;
    private List<Trabajador> listaTrab;
    private Faena faenaSeleccionada;
    private String rutBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionFaenasBean() {
    }

    @PostConstruct
    public void init() {
        this.listaFaenas = faenaFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Faena trab = (Faena) event.getObject();
            faenaFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Faena editada exitosamente.", ((Faena) event.getObject()).getIdFaena().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error  editando Faena.", e);
        }
    }

    

    public void eliminarFaena() {
        try {
            if (this.getListaFaenas() != null && !this.getListaFaenas().isEmpty() && this.getFaenaSeleccionada() != null) {
                this.getListaFaenas().remove(this.getFaenaSeleccionada());
                faenaFacade.remove(this.getFaenaSeleccionada());
                this.faenaSeleccionada = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faena eliminada exitosamente.", "Faena eliminada exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos una Faena de la tabla.", "Debe seleccionar al menos un Trabajador de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando Faena.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando Faena.", "Error grave eliminando Faena.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }
    
    
    public void actualizarFaena() {
        try {
            if (this.getFaenaSeleccionada() != null ) {
                Faena resTrab = null;
                
                    resTrab = this.getFaenaSeleccionada();
                    resTrab.setEstado("Terminada");
                    faenaFacade.edit(resTrab);
                

                this.setFaenaSeleccionada(null);
                

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faena actualizada exitosamente.", "Faena actualizada exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos una faena de la tabla.", "Debe seleccionar al menos una faena de la tabla.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            logger.error("Error grave actualizando estados de resumenes.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave actualizando resumen de trabajos.", "Error grave actualizando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void listarTrab() {
        try {
             
             listaTrab = trabFacade.obtenerTrabajadorPorFaena(faenaSeleccionada.getIdFaena());
             
             if (listaTrab.isEmpty()) {
                
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay trabajadores en esta faena.", "No hay trabajadores en esta faena.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            

        } catch (Exception e) {
            logger.error("Error grave listando resumen de trabajos", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave listando resumen de trabajos.", "Error grave listando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void setListaFaenas(List<Faena> listaFaenas) {
        this.listaFaenas = listaFaenas;
    }

    public void setFaenaSeleccionada(Faena faenaSeleccionada) {
        this.faenaSeleccionada = faenaSeleccionada;
    }

    public void setRutBusqueda(String rutBusqueda) {
        this.rutBusqueda = rutBusqueda;
    }

    public void setDvBusqueda(String dvBusqueda) {
        this.dvBusqueda = dvBusqueda;
    }

   
  public List<Faena> getListaFaenas() {
        return listaFaenas;
    }

    public Faena getFaenaSeleccionada() {
        return faenaSeleccionada;
    }

    public String getRutBusqueda() {
        return rutBusqueda;
    }

    public String getDvBusqueda() {
        return dvBusqueda;
    }

    public List<Trabajador> getListaTrab() {
        return listaTrab;
    }

    public void setListaTrab(List<Trabajador> listaTrab) {
        this.listaTrab = listaTrab;
    }
    

  
}
