/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.vo;

/**
 *
 * @author Esteban Perez
 */
public class ResumenTrabajoVO {
    
    private long idTrabajador;
    private long idResumenTrabajo;
    private String nombreTrabajador;
    private String apellidoP;
    private String apellidoM;
    private int rut;
    private String fechaFormateadaTrabajo;
    private long totalPagar;
    
    public ResumenTrabajoVO(){
       this.idTrabajador = 0;
       this.idResumenTrabajo = 0;
       this.nombreTrabajador = "";
       this.rut = 0;
       this.fechaFormateadaTrabajo = "";
       this.totalPagar = 0;
    }

    public ResumenTrabajoVO(long idTrabajador, long idResumenTrabajo, String nombreTrabajador, String apellidoP, String apellidoM, int rut, String fechaFormateadaTrabajo, long totalPagar) {
        this.idTrabajador = idTrabajador;
        this.idResumenTrabajo = idResumenTrabajo;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.rut = rut;
        this.fechaFormateadaTrabajo = fechaFormateadaTrabajo;
        this.totalPagar = totalPagar;
    }

    public long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public long getIdResumenTrabajo() {
        return idResumenTrabajo;
    }

    public void setIdResumenTrabajo(long idResumenTrabajo) {
        this.idResumenTrabajo = idResumenTrabajo;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getFechaFormateadaTrabajo() {
        return fechaFormateadaTrabajo;
    }

    public void setFechaFormateadaTrabajo(String fechaFormateadaTrabajo) {
        this.fechaFormateadaTrabajo = fechaFormateadaTrabajo;
    }

    public long getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(long totalPagar) {
        this.totalPagar = totalPagar;
    }   
}
