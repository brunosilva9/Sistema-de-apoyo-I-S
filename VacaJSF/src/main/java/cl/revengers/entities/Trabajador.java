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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban Perez
 */
@Entity
@Table(name = "trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t")
    , @NamedQuery(name = "Trabajador.findByIdTrabajador", query = "SELECT t FROM Trabajador t WHERE t.idTrabajador = :idTrabajador")
    , @NamedQuery(name = "Trabajador.findByRut", query = "SELECT t FROM Trabajador t WHERE t.rut = :rut")
    , @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT t FROM Trabajador t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Trabajador.findByApellidoP", query = "SELECT t FROM Trabajador t WHERE t.apellidoP = :apellidoP")
    , @NamedQuery(name = "Trabajador.findByApellidoM", query = "SELECT t FROM Trabajador t WHERE t.apellidoM = :apellidoM")
    , @NamedQuery(name = "Trabajador.findByDireccion", query = "SELECT t FROM Trabajador t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Trabajador.findByFechNac", query = "SELECT t FROM Trabajador t WHERE t.fechNac = :fechNac")
    , @NamedQuery(name = "Trabajador.findByPrevision", query = "SELECT t FROM Trabajador t WHERE t.prevision = :prevision")
    , @NamedQuery(name = "Trabajador.findByECivil", query = "SELECT t FROM Trabajador t WHERE t.eCivil = :eCivil")
    , @NamedQuery(name = "Trabajador.findByTelefono", query = "SELECT t FROM Trabajador t WHERE t.telefono = :telefono")})
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Trabajador")
    private Integer idTrabajador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rut")
    private int rut;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "apellidoP")
    private String apellidoP;
    @Size(max = 45)
    @Column(name = "apellidoM")
    private String apellidoM;
    @Size(max = 45)
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "fech_nac")
    @Temporal(TemporalType.DATE)
    private Date fechNac;
    @Size(max = 45)
    @Column(name = "prevision")
    private String prevision;
    @Size(max = 45)
    @Column(name = "e_Civil")
    private String eCivil;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTraba")
    private List<Prestamo> prestamoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrab")
    private List<Adelanto> adelantoList;
    @OneToMany(mappedBy = "idTrabajador")
    private List<ResumenTrabajo> resumenTrabajoList;

    public Trabajador() {
    }

    public Trabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Trabajador(Integer idTrabajador, int rut) {
        this.idTrabajador = idTrabajador;
        this.rut = rut;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechNac() {
        return fechNac;
    }

    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    public String getECivil() {
        return eCivil;
    }

    public void setECivil(String eCivil) {
        this.eCivil = eCivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @XmlTransient
    public List<Adelanto> getAdelantoList() {
        return adelantoList;
    }

    public void setAdelantoList(List<Adelanto> adelantoList) {
        this.adelantoList = adelantoList;
    }

    @XmlTransient
    public List<ResumenTrabajo> getResumenTrabajoList() {
        return resumenTrabajoList;
    }

    public void setResumenTrabajoList(List<ResumenTrabajo> resumenTrabajoList) {
        this.resumenTrabajoList = resumenTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajador != null ? idTrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.idTrabajador == null && other.idTrabajador != null) || (this.idTrabajador != null && !this.idTrabajador.equals(other.idTrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.revengers.entities.Trabajador[ idTrabajador=" + idTrabajador + " ]";
    }
    
}
