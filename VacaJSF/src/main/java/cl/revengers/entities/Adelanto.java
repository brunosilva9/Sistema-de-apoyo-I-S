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
@Table(name = "adelanto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adelanto.findAll", query = "SELECT a FROM Adelanto a")
    , @NamedQuery(name = "Adelanto.findByIdAdelanto", query = "SELECT a FROM Adelanto a WHERE a.idAdelanto = :idAdelanto")
    , @NamedQuery(name = "Adelanto.findByMonto", query = "SELECT a FROM Adelanto a WHERE a.monto = :monto")
    , @NamedQuery(name = "Adelanto.findByDescontado", query = "SELECT a FROM Adelanto a WHERE a.descontado = :descontado")})
public class Adelanto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Adelanto")
    private Integer idAdelanto;
    @Column(name = "Monto")
    private Integer monto;
    @Column(name = "descontado")
    private Boolean descontado;
    @JoinColumn(name = "id_Trab", referencedColumnName = "id_Trabajador")
    @ManyToOne(optional = false)
    private Trabajador idTrab;

    public Adelanto() {
    }

    public Adelanto(Integer idAdelanto) {
        this.idAdelanto = idAdelanto;
    }

    public Integer getIdAdelanto() {
        return idAdelanto;
    }

    public void setIdAdelanto(Integer idAdelanto) {
        this.idAdelanto = idAdelanto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Boolean getDescontado() {
        return descontado;
    }

    public void setDescontado(Boolean descontado) {
        this.descontado = descontado;
    }

    public Trabajador getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(Trabajador idTrab) {
        this.idTrab = idTrab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdelanto != null ? idAdelanto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adelanto)) {
            return false;
        }
        Adelanto other = (Adelanto) object;
        if ((this.idAdelanto == null && other.idAdelanto != null) || (this.idAdelanto != null && !this.idAdelanto.equals(other.idAdelanto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.Adelanto[ idAdelanto=" + idAdelanto + " ]";
    }
    
}
