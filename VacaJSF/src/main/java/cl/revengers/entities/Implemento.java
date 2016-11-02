/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "implemento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Implemento.findAll", query = "SELECT i FROM Implemento i")
    , @NamedQuery(name = "Implemento.findByIdImp", query = "SELECT i FROM Implemento i WHERE i.idImp = :idImp")
    , @NamedQuery(name = "Implemento.findByNombre", query = "SELECT i FROM Implemento i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Implemento.findByVidaUtil", query = "SELECT i FROM Implemento i WHERE i.vidaUtil = :vidaUtil")})
public class Implemento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Imp")
    private Integer idImp;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "vida_Util")
    private String vidaUtil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImp")
    private List<Prestamo> prestamoList;

    public Implemento() {
    }

    public Implemento(Integer idImp) {
        this.idImp = idImp;
    }

    public Integer getIdImp() {
        return idImp;
    }

    public void setIdImp(Integer idImp) {
        this.idImp = idImp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(String vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImp != null ? idImp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Implemento)) {
            return false;
        }
        Implemento other = (Implemento) object;
        if ((this.idImp == null && other.idImp != null) || (this.idImp != null && !this.idImp.equals(other.idImp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.Implemento[ idImp=" + idImp + " ]";
    }
    
}
