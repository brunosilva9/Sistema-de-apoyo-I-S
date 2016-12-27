/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.DiaTrabajo;
import cl.revengers.entities.ResumenProductos;
import cl.revengers.sb.ResumenProdFacadeLocal;
import cl.revengers.sb.DiaTrabajoFacadeLocal;
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


@Named(value = "gestionDiaBean")
@ViewScoped
public class GestionDiaBean implements Serializable {

    @EJB
    private DiaTrabajoFacadeLocal diaFacade;
    @EJB
    private ResumenProdFacadeLocal resFacade;

    private final static Logger logger = Logger.getLogger(GestionDiaBean.class);

    private List<DiaTrabajo> listaDias;
    
    private List<ResumenProductos> listaRes;
    private DiaTrabajo diaSeleccionado;
    private String rutBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionDiaBean() {
    }

    @PostConstruct
    public void init() {
        this.listaDias = diaFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            DiaTrabajo trab = (DiaTrabajo) event.getObject();
            diaFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Trabajador editado exitosamente.", ((DiaTrabajo) event.getObject()).getIdDia().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando trabajador.", e);
        }
    }

    

    public void eliminarDia() {
        try {
            if (this.getListaDias() != null && !this.getListaDias().isEmpty() && this.getDiaSeleccionado() != null) {
                this.getListaDias().remove(this.getDiaSeleccionado());
                diaFacade.remove(this.getDiaSeleccionado());
                this.diaSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faena eliminado exitosamente.", "Faena eliminado exitosamente.");
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
    
      public void listarTrabajo() {
        try {
             listaRes = resFacade.obtenerResrPorDia(diaSeleccionado.getIdDia());
            

        } catch (Exception e) {
            logger.error("Error grave listando resumen de trabajos", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave listando resumen de trabajos.", "Error grave listando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
 

    public List<DiaTrabajo> getListaDias() {
        return listaDias;
    }

    public DiaTrabajo getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public String getRutBusqueda() {
        return rutBusqueda;
    }

    public String getDvBusqueda() {
        return dvBusqueda;
    }

    public void setListaDias(List<DiaTrabajo> listaDias) {
        this.listaDias = listaDias;
    }

    public void setDiaSeleccionado(DiaTrabajo diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public void setRutBusqueda(String rutBusqueda) {
        this.rutBusqueda = rutBusqueda;
    }

    public void setDvBusqueda(String dvBusqueda) {
        this.dvBusqueda = dvBusqueda;
    }

    public List<ResumenProductos> getListaRes() {
        return listaRes;
    }

    public void setListaRes(List<ResumenProductos> listaRes) {
        this.listaRes = listaRes;
    }
    


  
}
