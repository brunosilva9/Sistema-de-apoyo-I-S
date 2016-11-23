package cl.revengers.backbeans;

import cl.revengers.entities.Implemento;
import cl.revengers.sb.ImplementoFacadeLocal;
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

/**
 *
 * @author Esteban Perez INNOVA-TI
 */
@Named(value = "gestionImplementosBean")
@ViewScoped
public class GestionImplementosBean implements Serializable {

    @EJB
    private ImplementoFacadeLocal implementoFacade;

    private final static Logger logger = Logger.getLogger(GestionImplementosBean.class);

    private List<Implemento> listaImplementos;
    private Implemento implementoSeleccionado;
    private String nombreBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionImplementosBean() {
    }

    @PostConstruct
    public void init() {
        this.listaImplementos = implementoFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Implemento trab = (Implemento) event.getObject();
            implementoFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Implemento editado exitosamente.", ((Implemento) event.getObject()).getIdImp().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando implemento.", e);
        }
    }

    public void eliminarImplemento() {
        try {
            if (this.getListaImplementos() != null && !this.getListaImplementos().isEmpty() && this.getImplementoSeleccionado() != null) {
                this.getListaImplementos().remove(this.getImplementoSeleccionado());
                implementoFacade.remove(this.getImplementoSeleccionado());
                this.implementoSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "implemento eliminado exitosamente.", "implemento eliminado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un implemento de la tabla.", "Debe seleccionar al menos un implemento de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando cliente.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando implemento.", "Error grave eliminando implemento.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }

    public List<Implemento> getListaImplementos() {
        return listaImplementos;
    }

    public void setListaImplemento(List<Implemento> listaImplementos) {
        this.listaImplementos = listaImplementos;
    }

    public Implemento getImplementoSeleccionado() {
        return implementoSeleccionado;
    }

    public void setImplementoSeleccionado(Implemento implementoSeleccionado) {
        this.implementoSeleccionado = implementoSeleccionado;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }

    public String getDvBusqueda() {
        return dvBusqueda;
    }

    public void setDvBusqueda(String dvBusqueda) {
        this.dvBusqueda = dvBusqueda;
    }
}
