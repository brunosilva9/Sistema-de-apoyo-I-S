/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "resumen_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenProductos.findAll", query = "SELECT r FROM ResumenProductos r")
    , @NamedQuery(name = "ResumenProductos.findByIdResProd", query = "SELECT r FROM ResumenProductos r WHERE r.idResProd = :idResProd")
    , @NamedQuery(name = "ResumenProductos.findByCantidad", query = "SELECT r FROM ResumenProductos r WHERE r.cantidad = :cantidad")})
public class ResumenProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Res_Prod")
    private Integer idResProd;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @JoinColumn(name = "id_Prod", referencedColumnName = "id_Producto")
    @ManyToOne
    private Producto idProd;
    @JoinColumn(name = "id_Resumen", referencedColumnName = "id_Resumen")
    @ManyToOne
    private ResumenTrabajo idResumen;

    public ResumenProductos() {
    }

    public ResumenProductos(Integer idResProd) {
        this.idResProd = idResProd;
    }

    public Integer getIdResProd() {
        return idResProd;
    }

    public void setIdResProd(Integer idResProd) {
        this.idResProd = idResProd;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getIdProd() {
        return idProd;
    }

    public void setIdProd(Producto idProd) {
        this.idProd = idProd;
    }

    public ResumenTrabajo getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(ResumenTrabajo idResumen) {
        this.idResumen = idResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResProd != null ? idResProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenProductos)) {
            return false;
        }
        ResumenProductos other = (ResumenProductos) object;
        if ((this.idResProd == null && other.idResProd != null) || (this.idResProd != null && !this.idResProd.equals(other.idResProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.ResumenProductos[ idResProd=" + idResProd + " ]";
    }
    
}
