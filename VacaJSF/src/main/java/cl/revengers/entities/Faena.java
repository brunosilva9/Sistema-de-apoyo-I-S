/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "faena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faena.findAll", query = "SELECT f FROM Faena f")
    , @NamedQuery(name = "Faena.findByIdFaena", query = "SELECT f FROM Faena f WHERE f.idFaena = :idFaena")
    , @NamedQuery(name = "Faena.findByFechIni", query = "SELECT f FROM Faena f WHERE f.fechIni = :fechIni")
    , @NamedQuery(name = "Faena.findByFechTer", query = "SELECT f FROM Faena f WHERE f.fechTer = :fechTer")
    , @NamedQuery(name = "Faena.findByUbicacion", query = "SELECT f FROM Faena f WHERE f.ubicacion = :ubicacion")
    , @NamedQuery(name = "Faena.findByEstado", query = "SELECT f FROM Faena f WHERE f.estado = :estado")
    , @NamedQuery(name = "Faena.findByDescripcion", query = "SELECT f FROM Faena f WHERE f.descripcion = :descripcion")})
public class Faena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Faena")
    private Integer idFaena;
    @Column(name = "fech_Ini")
    @Temporal(TemporalType.DATE)
    private Date fechIni;
    @Column(name = "fech_Ter")
    @Temporal(TemporalType.DATE)
    private Date fechTer;
    @Size(max = 45)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_Cliente")
    @ManyToOne
    private Cliente idCliente;
    @OneToMany(mappedBy = "idFaena")
    private List<DiaTrabajo> diaTrabajoList;

    public Faena() {
    }

    public Faena(Integer idFaena) {
        this.idFaena = idFaena;
    }

    public Integer getIdFaena() {
        return idFaena;
    }

    public void setIdFaena(Integer idFaena) {
        this.idFaena = idFaena;
    }

    public Date getFechIni() {
        return fechIni;
    }

    public void setFechIni(Date fechIni) {
        this.fechIni = fechIni;
    }

    public Date getFechTer() {
        return fechTer;
    }

    public void setFechTer(Date fechTer) {
        this.fechTer = fechTer;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public List<DiaTrabajo> getDiaTrabajoList() {
        return diaTrabajoList;
    }

    public void setDiaTrabajoList(List<DiaTrabajo> diaTrabajoList) {
        this.diaTrabajoList = diaTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaena != null ? idFaena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faena)) {
            return false;
        }
        Faena other = (Faena) object;
        if ((this.idFaena == null && other.idFaena != null) || (this.idFaena != null && !this.idFaena.equals(other.idFaena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.Faena[ idFaena=" + idFaena + " ]";
    }
    
}
