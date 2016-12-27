/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.sb.ProductoFacadeLocal;
import cl.revengers.sb.DiaTrabajoFacadeLocal;
import cl.revengers.sb.ImplementoFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author cristopherandres
 */
//@Named(value = "aplicationBean")
@ManagedBean(name = "aplicationBean")
@RequestScoped
public class AplicationBean implements Serializable {

    @EJB
    private ProductoFacadeLocal productoFacade;
    @EJB
    private DiaTrabajoFacadeLocal diaFacade;
    @EJB
    private ImplementoFacadeLocal impFacade;

    
    /**
     * Creates a new instance of AplicationBean
     */
    public AplicationBean() {
    }

    public ProductoFacadeLocal getProductoFacade() {
        return productoFacade;
    }
    
    
    public DiaTrabajoFacadeLocal getDiaFacade() {
        return diaFacade;
    }
    
    public ImplementoFacadeLocal getImpFacade() {
        return impFacade;
    }
    
    
}
