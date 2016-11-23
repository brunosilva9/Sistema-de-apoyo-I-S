/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Cliente;
import cl.revengers.sb.ClienteFacadeLocal;
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
@Named(value = "gestionClientesBean")
@ViewScoped
public class GestionClientesBean implements Serializable {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private final static Logger logger = Logger.getLogger(GestionClientesBean.class);

    private List<Cliente> listaClientes;
    private Cliente clienteSeleccionado;
    private String rutBusqueda;
    private String dvBusqueda;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public GestionClientesBean() {
    }

    @PostConstruct
    public void init() {
        this.listaClientes = clienteFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Cliente trab = (Cliente) event.getObject();
            clienteFacade.edit(trab);
            FacesMessage msg = new FacesMessage("Cliente editado exitosamente.", ((Cliente) event.getObject()).getIdCliente().toString());
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", msg);
        } catch (Exception e) {
            logger.error("Error grave editando trabajador.", e);
        }
    }

    public void eliminarCliente() {
        try {
            if (this.getListaClientes() != null && !this.getListaClientes().isEmpty() && this.getClienteSeleccionado() != null) {
                this.getListaClientes().remove(this.getClienteSeleccionado());
                clienteFacade.remove(this.getClienteSeleccionado());
                this.clienteSeleccionado = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente eliminado exitosamente.", "Cliente eliminado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un Cliente de la tabla.", "Debe seleccionar al menos un Cliente de la tabla.");
                FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaClie:mensajePantallaGrilla", message);
            }

        } catch (Exception e) {
            logger.error("Error grave eliminando cliente.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error grave eliminando Cliente.", "Error grave eliminando Cliente.");
            FacesContext.getCurrentInstance().addMessage(":templateForm:formGrillaTrab:mensajePantallaGrilla", message);
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaTrabajadores(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
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
