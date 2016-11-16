package cl.revengers.backbeans;

import cl.revengers.entities.Implemento;
import cl.revengers.sb.ImplementoFacadeLocal;
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

 */
@Named(value = "ingresoImplementoBean")
@ViewScoped
public class IngresoImplementoBean implements Serializable {

    @EJB
    private ImplementoFacadeLocal implementoFacade;

    private final static Logger logger = Logger.getLogger(IngresoImplementoBean.class);

    private String nombreImplemento;
    
    private String vidaUtil;
  

    public IngresoImplementoBean() {
    }

    public void agregarImplemento() {
        Implemento trab = null;
        try {

            trab = implementoFacade.getImplementoByNombre(this.getNombreImplemento());

            if (trab == null) {
                trab = new Implemento();
                trab.setIdImp(0);
               
                trab.setNombre(this.getNombreImplemento().trim());
                trab.setVidaUtil(this.getVidaUtil());
                
              
                implementoFacade.create(trab);
                this.limpiarValores();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Implemento ingresado exitosamente.", "Implemento ingresado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, implemento ya existe en base de datos.", "Error, implemento ya existe en base de datos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            logger.error("Error grave creando implemento.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando implemento.", "Error grave creando implemento.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setNombreImplemento("");
            
            this.setVidaUtil("");
            
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public String getNombreImplemento() {
        return nombreImplemento;
    }

    public void setNombreImplemento(String nombreImplemento) {
        this.nombreImplemento = nombreImplemento;
    }

 
   

    public String getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(String vidaUtil) {
        this.vidaUtil = vidaUtil;
    }
}

    