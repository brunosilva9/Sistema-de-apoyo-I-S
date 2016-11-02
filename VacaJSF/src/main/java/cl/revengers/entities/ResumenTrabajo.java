/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "resumen_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenTrabajo.findAll", query = "SELECT r FROM ResumenTrabajo r")
    , @NamedQuery(name = "ResumenTrabajo.findByIdResumen", query = "SELECT r FROM ResumenTrabajo r WHERE r.idResumen = :idResumen")
    , @NamedQuery(name = "ResumenTrabajo.findByEstado", query = "SELECT r FROM ResumenTrabajo r WHERE r.estado = :estado")})
public class ResumenTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Resumen")
    private Integer idResumen;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idResumen")
    private List<ResumenProductos> resumenProductosList;
    @JoinColumn(name = "id_Dia", referencedColumnName = "id_Dia")
    @ManyToOne
    private DiaTrabajo idDia;
    @JoinColumn(name = "id_Trabajador", referencedColumnName = "id_Trabajador")
    @ManyToOne
    private Trabajador idTrabajador;

    public ResumenTrabajo() {
    }

    public ResumenTrabajo(Integer idResumen) {
        this.idResumen = idResumen;
    }

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<ResumenProductos> getResumenProductosList() {
        return resumenProductosList;
    }

    public void setResumenProductosList(List<ResumenProductos> resumenProductosList) {
        this.resumenProductosList = resumenProductosList;
    }

    public DiaTrabajo getIdDia() {
        return idDia;
    }

    public void setIdDia(DiaTrabajo idDia) {
        this.idDia = idDia;
    }

    public Trabajador getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Trabajador idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResumen != null ? idResumen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenTrabajo)) {
            return false;
        }
        ResumenTrabajo other = (ResumenTrabajo) object;
        if ((this.idResumen == null && other.idResumen != null) || (this.idResumen != null && !this.idResumen.equals(other.idResumen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.ResumenTrabajo[ idResumen=" + idResumen + " ]";
    }
    
}
