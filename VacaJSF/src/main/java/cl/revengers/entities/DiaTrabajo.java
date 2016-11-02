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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "dia_Trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaTrabajo.findAll", query = "SELECT d FROM DiaTrabajo d")
    , @NamedQuery(name = "DiaTrabajo.findByIdDia", query = "SELECT d FROM DiaTrabajo d WHERE d.idDia = :idDia")
    , @NamedQuery(name = "DiaTrabajo.findByFechaD", query = "SELECT d FROM DiaTrabajo d WHERE d.fechaD = :fechaD")
    , @NamedQuery(name = "DiaTrabajo.findByCostoTransporte", query = "SELECT d FROM DiaTrabajo d WHERE d.costoTransporte = :costoTransporte")
    , @NamedQuery(name = "DiaTrabajo.findByCostoSupervision", query = "SELECT d FROM DiaTrabajo d WHERE d.costoSupervision = :costoSupervision")})
public class DiaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Dia")
    private Integer idDia;
    @Column(name = "fechaD")
    @Temporal(TemporalType.DATE)
    private Date fechaD;
    @Column(name = "costo_Transporte")
    private Integer costoTransporte;
    @Column(name = "costo_Supervision")
    private Integer costoSupervision;
    @OneToMany(mappedBy = "idDia")
    private List<ResumenTrabajo> resumenTrabajoList;
    @JoinColumn(name = "id_Faena", referencedColumnName = "id_Faena")
    @ManyToOne
    private Faena idFaena;

    public DiaTrabajo() {
    }

    public DiaTrabajo(Integer idDia) {
        this.idDia = idDia;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public Date getFechaD() {
        return fechaD;
    }

    public void setFechaD(Date fechaD) {
        this.fechaD = fechaD;
    }

    public Integer getCostoTransporte() {
        return costoTransporte;
    }

    public void setCostoTransporte(Integer costoTransporte) {
        this.costoTransporte = costoTransporte;
    }

    public Integer getCostoSupervision() {
        return costoSupervision;
    }

    public void setCostoSupervision(Integer costoSupervision) {
        this.costoSupervision = costoSupervision;
    }

    @XmlTransient
    public List<ResumenTrabajo> getResumenTrabajoList() {
        return resumenTrabajoList;
    }

    public void setResumenTrabajoList(List<ResumenTrabajo> resumenTrabajoList) {
        this.resumenTrabajoList = resumenTrabajoList;
    }

    public Faena getIdFaena() {
        return idFaena;
    }

    public void setIdFaena(Faena idFaena) {
        this.idFaena = idFaena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaTrabajo)) {
            return false;
        }
        DiaTrabajo other = (DiaTrabajo) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.DiaTrabajo[ idDia=" + idDia + " ]";
    }
    
}
