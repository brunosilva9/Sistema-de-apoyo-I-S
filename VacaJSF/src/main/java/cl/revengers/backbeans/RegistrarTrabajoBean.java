/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.Producto;
import cl.revengers.vo.trabVO;

import cl.revengers.entities.ResumenTrabajo;
import cl.revengers.entities.ResumenProductos;
import cl.revengers.entities.Trabajador;
import cl.revengers.entities.DiaTrabajo;
import cl.revengers.sb.ProductoFacadeLocal;
import cl.revengers.sb.ResumenTrabajoFacadeLocal;
import cl.revengers.sb.ResumenProdFacadeLocal;
import cl.revengers.sb.TrabajadorFacadeLocal;
import cl.revengers.sb.DiaTrabajoFacadeLocal;
import cl.revengers.vo.DiaTrabajoVO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

@Named(value = "registrarTrabajoBean")
@ViewScoped
public class RegistrarTrabajoBean implements Serializable {

    @EJB
    private ProductoFacadeLocal productoFacade;
    @EJB
    private DiaTrabajoFacadeLocal diaFacade;

    @EJB
    private TrabajadorFacadeLocal trabajadorFacade;
    @EJB
    private ResumenTrabajoFacadeLocal resumenTrabajoFacade;

    @EJB
    private ResumenProdFacadeLocal resumenProdFacade;

    private final static Logger logger = Logger.getLogger(RegistrarTrabajoBean.class);

    private List<Producto> listaProductos;
    private Producto productoSeleccionado;

    private List<Trabajador> listaTrabajadores;
    private List<trabVO> ListaTrabSeleccionados;
    private Trabajador trabajadorSeleccionado;
    private List<trabVO> listaTrab;

    private Trabajador idTrabajador;
    private trabVO idTrabVo;
    private String estado;

    private ResumenTrabajo idRes;
    private Producto idProducto;
    private int cantidad;

    private List<DiaTrabajo> listaDiasTrabajo;
    private DiaTrabajo diaTrabajoSeleccionado;

    /**
     * Creates a new instance of GestionClientesBean
     */
    public RegistrarTrabajoBean() {
    }

    @PostConstruct
    public void init() {
        try {
            /*if(listaDiasOriginal != null && !listaDiasOriginal.isEmpty()){
                this.listaDias = new ArrayList<>();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for(DiaTrabajo dia : listaDiasOriginal){
                    DiaTrabajoVO diaTrab = new DiaTrabajoVO();
                    diaTrab.setCodigo(dia.getIdDia());
                    diaTrab.setFecha(dia.getFechaD());
                    diaTrab.setFechaFormat(sdf.format(dia.getFechaD()));
                    listaDias.add(diaTrab);
                }
            }*/

            this.listaProductos = productoFacade.findAll();
            this.listaDiasTrabajo = diaFacade.findAll();
            this.listaTrabajadores = trabajadorFacade.findAll();

            List<trabVO> listaTrabVO = new ArrayList<>();
            trabVO resTrabVO = null;

            for (Trabajador res : listaTrabajadores) {
                resTrabVO = new trabVO();
                resTrabVO.setIdTrabajador(res);
                resTrabVO.setNombreTrabajador(res.getNombre().trim());
                resTrabVO.setApellidoP(res.getApellidoP().trim());
                resTrabVO.setRut(res.getRut());
                resTrabVO.setCantidad(cantidad);
                listaTrabVO.add(resTrabVO);
            }

            this.setListaTrab(listaTrabVO);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Trabajadores listados", "Trabajadores listados");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            logger.error("Error grave listando resumen de trabajos", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave listando resumen de trabajos.", "Error grave listando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void crearResumen() {
        ResumenTrabajo trab = null;
        ResumenProductos trab2 = null;
        try {

            if (this.getProductoSeleccionado() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un producto.", "Error: Debe seleccionar un producto.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            if (this.getDiaTrabajoSeleccionado() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un dia.", "Error: Debe seleccionar un dia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            if (this.ListaTrabSeleccionados== null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Debe seleccionar un Trabajador.", "Error: Debe seleccionar un Trabajador.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            
            for (trabVO trabajador : this.getListaTrabSeleccionados()) {
            trab = new ResumenTrabajo();
            trab.setIdResumen(0);
            trab.setIdTrabajador(trabajador.getIdTrabajador());
            trab.setEstado("no pagado");

            //DiaTrabajo diaTrabSel = diaFacade.find((int) this.getDiaSeleccionado().getCodigo());
            trab.setIdDia(this.getDiaTrabajoSeleccionado());

            resumenTrabajoFacade.create(trab);

            trab2 = new ResumenProductos();
            trab2.setCantidad(trabajador.getCantidad());
            trab2.setIdProd(this.getProductoSeleccionado());
            trab2.setIdResumen(trab);
            resumenProdFacade.create(trab2);
            }
            this.limpiarValores();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resumen trabajo creado exitosamente.", "Resumen trabajo creada exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            logger.error("Error grave creando resumen trabajo.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave creando resumen trabajo .", "Error grave creando resumen trabajo.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void limpiarValores() {
        try {
            this.setEstado("");
            this.setIdProducto(null);
            this.setIdTrabajador(null);
            this.setDiaTrabajoSeleccionado(null);
            this.setIdRes(null);
            this.setCantidad(0);

        } catch (Exception e) {
            logger.error("Error grave limpiando valores formulario", e);
            throw new RuntimeException(e);
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public Trabajador getTrabajadorSeleccionado() {
        return trabajadorSeleccionado;
    }

    public void setTrabajadorSeleccionado(Trabajador trabajadorSeleccionado) {
        this.trabajadorSeleccionado = trabajadorSeleccionado;
    }

    public Trabajador getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Trabajador idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ResumenTrabajo getIdRes() {
        return idRes;
    }

    public void setIdRes(ResumenTrabajo idRes) {
        this.idRes = idRes;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<trabVO> getListaTrab() {
        return listaTrab;
    }

    public void setListaTrab(List<trabVO> listaTrab) {
        this.listaTrab = listaTrab;
    }

    public trabVO getIdTrabVo() {
        return idTrabVo;
    }

    public void setIdTrabVo(trabVO idTrabVo) {
        this.idTrabVo = idTrabVo;
    }

    public List<DiaTrabajo> getListaDiasTrabajo() {
        return listaDiasTrabajo;
    }

    public void setListaDiasTrabajo(List<DiaTrabajo> listaDiasTrabajo) {
        this.listaDiasTrabajo = listaDiasTrabajo;
    }

    public DiaTrabajo getDiaTrabajoSeleccionado() {
        return diaTrabajoSeleccionado;
    }

    public void setDiaTrabajoSeleccionado(DiaTrabajo diaTrabajoSeleccionado) {
        this.diaTrabajoSeleccionado = diaTrabajoSeleccionado;
    }

    public List<trabVO> getListaTrabSeleccionados() {
        return ListaTrabSeleccionados;
    }

    public void setListaTrabSeleccionados(List<trabVO> ListaTrabSeleccionados) {
        this.ListaTrabSeleccionados = ListaTrabSeleccionados;
    }
    
    
}
