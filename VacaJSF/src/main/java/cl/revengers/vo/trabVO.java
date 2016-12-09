/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.vo;

import cl.revengers.entities.Trabajador;

/**
 *
 * @author Esteban Perez
 */
public class trabVO {
    
    private Trabajador idTrabajador;
    
    private String nombreTrabajador;
    private String apellidoP;
    
    private int rut;
    
    private int cantidad;
    
    public trabVO(){
       this.idTrabajador = null;
       
       this.nombreTrabajador = "";
       this.apellidoP = "";
       this.rut = 0;
       this.cantidad=0;
    }

    public trabVO(Trabajador idTrabajador, String nombreTrabajador, String apellidoP, int rut, int cantidad) {
        this.idTrabajador = idTrabajador;
        
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoP = apellidoP;
        
        this.rut = rut;
        this.cantidad = cantidad;
        
    }

    public Trabajador getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Trabajador idTrabajador) {
        this.idTrabajador = idTrabajador;
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

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    

    
}
