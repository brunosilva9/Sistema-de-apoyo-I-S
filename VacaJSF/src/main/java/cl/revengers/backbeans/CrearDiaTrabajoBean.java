/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.DiaTrabajo;
import cl.revengers.entities.Faena;
import cl.revengers.sb.DiaTrabajoFacadeLocal;
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
 * @author Esteban Perez INNOVA-TI
 */
@Named(value = "crearDiaTrabajoBean")
@ViewScoped
public class CrearDiaTrabajoBean implements Serializable {

    @EJB
    private DiaTrabajoFacadeLocal diaTrabajoFacade;

    private final static Logger logger = Logger.getLogger(CrearDiaTrabajoBean.class);

    private int costoTran;
    private int costoSuper;
    
    private Faena idFaena;
    
    
    
    private Date fecha;
    
    
    
   
    public CrearDiaTrabajoBean() {
    }

    public void crearDiaTrabajo() {
        DiaTrabajo trab = null;
        try {

            

            
                trab = new DiaTrabajo();
                trab.setIdDia(0);
                trab.setIdFaena(this.getIdFaena());
                trab.setCostoSupervision(this.getCostoSuper());
                trab.setCostoTransporte(this.getCostoTran());
                trab.setFechaD(this.getFecha());
                
                
                
                diaTrabajoFacade.create(trab);
                this.limpiarValores();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dia creado exitosamente.", "Dia creado exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);
           
        } catch (Exception e) {
            logger.error("Error grave creando Dia.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando Dia.", "Error grave creando Dia.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setCostoTran(0);
            this.setCostoSuper(0);
            
            this.setIdFaena(null);
           
            this.setFecha(null);
            
            
            
        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public int getCostoTran() {
        return costoTran;
    }

    public int getCostoSuper() {
        return costoSuper;
    }

    public Faena getIdFaena() {
        return idFaena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setCostoTran(int costoTran) {
        this.costoTran = costoTran;
    }

    public void setCostoSuper(int costoSuper) {
        this.costoSuper = costoSuper;
    }

    public void setIdFaena(Faena idFaena) {
        this.idFaena = idFaena;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    



   
}
