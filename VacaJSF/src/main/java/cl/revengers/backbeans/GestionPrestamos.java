package cl.revengers.backbeans;

import cl.revengers.entities.Prestamo;
import cl.revengers.sb.PrestamoFacadeLocal;
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
@Named(value = "gestionPrestamos")
@ViewScoped
public class GestionPrestamos implements Serializable {

    @EJB
    private PrestamoFacadeLocal prestamoFacade;

    private final static Logger logger = Logger.getLogger(GestionPrestamos.class);

    private List<Prestamo> listaP;
    private Prestamo implementoSeleccionado;
    

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionPrestamos() {
    }

    @PostConstruct
    public void init() {
        this.listaP = prestamoFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Prestamo trab = (Prestamo) event.getObject();
            prestamoFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Prestamo editado exitosamente.", ((Prestamo) event.getObject()).getIdImp().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando Prestamo.", e);
        }
    }

    public void eliminarPrestamo() {
        try {
            if (this.getListaP() != null && !this.getListaP().isEmpty() && this.getImplementoSeleccionado() != null) {
                this.getListaP().remove(this.getImplementoSeleccionado());
                prestamoFacade.remove(this.getImplementoSeleccionado());
                this.implementoSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "prestamo eliminado exitosamente.", "prestamo eliminado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un prestamo de la tabla.", "Debe seleccionar al menos un prestamo de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando prestamo.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando prestamo.", "Error grave eliminando prestamo.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }

    public List<Prestamo> getListaP() {
        return listaP;
    }

    public void setListaP(List<Prestamo> listaP) {
        this.listaP = listaP;
    }

    public Prestamo getImplementoSeleccionado() {
        return implementoSeleccionado;
    }

    public void setImplementoSeleccionado(Prestamo implementoSeleccionado) {
        this.implementoSeleccionado = implementoSeleccionado;
    }

  
}
