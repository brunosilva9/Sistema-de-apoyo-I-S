package cl.revengers.backbeans;

import cl.revengers.entities.Producto;
import cl.revengers.sb.ProductoFacadeLocal;
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
@Named(value = "ingresoProductoBean")
@ViewScoped
public class IngresoProductoBean implements Serializable {

    @EJB
    private ProductoFacadeLocal productoFacade;

    private final static Logger logger = Logger.getLogger(IngresoProductoBean.class);

    private String nombreProducto;
    
    private int valor;
    private int valorp;
  

    public IngresoProductoBean() {
    }

    public void agregarProducto() {
        Producto trab = null;
        try {

            trab = productoFacade.getProductoByNombre(this.getNombreProducto());

            if (trab == null) {
                trab = new Producto();
                trab.setIdProducto(0);
               
                trab.setNombre(this.getNombreProducto().trim());
                trab.setValor(this.getValor());
                trab.setPrecioP(this.getValorp());
                
              
                productoFacade.create(trab);
                this.limpiarValores();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto ingresado exitosamente.", "Producto ingresado exitosamente.");
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
            this.setNombreProducto("");
            
            this.setValor(0);
            this.setValorp(0);
            
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

 
   

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getValorp() {
        return valorp;
    }

    public void setValorp(int valorp) {
        this.valorp = valorp;
    }
}

    