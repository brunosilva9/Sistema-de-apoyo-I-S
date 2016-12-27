/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.vo;

import java.util.Date;

/**
 *
 * @author cristopherandres
 */
public class DiaTrabajoVO {
    
    private long codigo;
    private Date fecha;
    private String fechaFormat;

    public DiaTrabajoVO() {
    }

    public DiaTrabajoVO(long codigo, Date fecha, String fechaFormat) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechaFormat = fechaFormat;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormat() {
        return fechaFormat;
    }

    public void setFechaFormat(String fechaFormat) {
        this.fechaFormat = fechaFormat;
    }
    
    
}
