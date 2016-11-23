/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Producto;
import cl.revengers.sb.ProductoFacadeLocal;
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
@Named(value = "gestionProductosBean")
@ViewScoped
public class GestionProductosBean implements Serializable {

    @EJB
    private ProductoFacadeLocal productoFacade;

    private final static Logger logger = Logger.getLogger(GestionProductosBean.class);

    private List<Producto> listaProductos;
    private Producto productoSeleccionado;
    private String nombreBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionProductosBean() {
    }

    @PostConstruct
    public void init() {
        this.listaProductos = productoFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Producto trab = (Producto) event.getObject();
            productoFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Producto editado exitosamente.", ((Producto) event.getObject()).getIdProducto().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando trabajador.", e);
        }
    }

    public void eliminarProducto() {
        try {
            if (this.getListaProductos() != null && !this.getListaProductos().isEmpty() && this.getProductoSeleccionado() != null) {
                this.getListaProductos().remove(this.getProductoSeleccionado());
                productoFacade.remove(this.getProductoSeleccionado());
                this.productoSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado exitosamente.", "Producto eliminado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un Producto de la tabla.", "Debe seleccionar al menos un Producto de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando Producto.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando Producto.", "Error grave eliminando Producto.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
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
