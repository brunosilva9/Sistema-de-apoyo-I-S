package cl.revengers.backbeans;

import cl.revengers.entities.Cliente;
import cl.revengers.sb.ClienteFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

/**
 *

 */
@Named(value = "ingresoClienteBean")
@ViewScoped
public class IngresoClienteBean implements Serializable {

    @EJB
    private ClienteFacadeLocal clienteFacade;

    private final static Logger logger = Logger.getLogger(IngresoClienteBean.class);

    private String nombreCliente;
    
    private int rutCliente;
  

    public IngresoClienteBean() {
    }

    public void agregarCliente() {
        Cliente trab = null;
        try {

            trab = clienteFacade.getClienteByRut(this.getRut());

            if (trab == null) {
                trab = new Cliente();
                trab.setIdCliente(0);
               
                trab.setNombre(this.getNombreCliente().trim());
                trab.setRutCliente(this.getRut());
                
              
                clienteFacade.create(trab);
                this.limpiarValores();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente ingresado exitosamente.", "Cliente ingresado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, implemento ya existe en base de datos.", "Error, implemento ya existe en base de datos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            logger.error("Error grave creando cliente.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando cliente.", "Error grave creando implemento.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setNombreCliente("");
            
            this.setRut(0);
            
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

 
   

    public int getRut() {
        return rutCliente;
    }

    public void setRut(int rutCliente) {
        this.rutCliente = rutCliente;
    }
}

    