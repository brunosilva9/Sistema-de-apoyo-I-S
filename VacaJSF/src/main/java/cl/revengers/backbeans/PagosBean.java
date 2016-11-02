/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.backbeans;

import cl.revengers.entities.ResumenProductos;
import cl.revengers.entities.ResumenTrabajo;
import cl.revengers.sb.ResumenTrabajoFacadeLocal;
import cl.revengers.utils.Constantes;
import cl.revengers.vo.ResumenTrabajoVO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Named(value = "pagosBean")
@ViewScoped
public class PagosBean implements Serializable {

    @EJB
    private ResumenTrabajoFacadeLocal resumenTrabajoFacade;

    private final static Logger logger = Logger.getLogger(PagosBean.class);

    /**
     * Creates a new instance of PagosBean
     */
    public PagosBean() {
    }

    private String rutBusqueda;
    private String dvBusqueda;
    private List<ResumenTrabajoVO> listaResumenes;
    private List<ResumenTrabajoVO> resumenesSeleccionados;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public String getRutBusqueda() {
        return rutBusqueda;
    }

    public void setRutBusqueda(String rutBusqueda) {
        this.rutBusqueda = rutBusqueda;
    }

    public String getDvBusqueda() {
        return dvBusqueda;
    }

    public void setDvBusqueda(String dvBusqueda) {
        this.dvBusqueda = dvBusqueda;
    }

    public List<ResumenTrabajoVO> getListaResumenes() {
        return listaResumenes;
    }

    public void setListaResumenes(List<ResumenTrabajoVO> listaResumenes) {
        this.listaResumenes = listaResumenes;
    }

    public List<ResumenTrabajoVO> getResumenesSeleccionados() {
        return resumenesSeleccionados;
    }

    public void setResumenesSeleccionados(List<ResumenTrabajoVO> resumenesSeleccionados) {
        this.resumenesSeleccionados = resumenesSeleccionados;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void listarTrabajos() {
        try {
            List<ResumenTrabajo> listaResumenesPorTrabajador = resumenTrabajoFacade.obtenerResumenesPorTrabajador(Integer.parseInt(this.getRutBusqueda()));
            List<ResumenTrabajoVO> listaResumenTrabajoVO = new ArrayList<>();
            ResumenTrabajoVO resTrabVO = null;

            if (listaResumenesPorTrabajador != null && !listaResumenesPorTrabajador.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                for (ResumenTrabajo res : listaResumenesPorTrabajador) {
                    long totalPagar = 0;
                    for (ResumenProductos resumenProd : res.getResumenProductosList()) {
                        totalPagar += (resumenProd.getCantidad() * resumenProd.getIdProd().getValor());
                    }
                    resTrabVO = new ResumenTrabajoVO();
                    resTrabVO.setIdResumenTrabajo(res.getIdResumen());
                    resTrabVO.setIdTrabajador(res.getIdTrabajador().getIdTrabajador());
                    resTrabVO.setNombreTrabajador(res.getIdTrabajador().getNombre().trim());
                    resTrabVO.setApellidoP(res.getIdTrabajador().getApellidoP().trim());
                    resTrabVO.setApellidoM(res.getIdTrabajador().getApellidoM().trim());
                    resTrabVO.setFechaFormateadaTrabajo(sdf.format(res.getIdDia().getFechaD()));
                    resTrabVO.setRut(res.getIdTrabajador().getRut());
                    resTrabVO.setTotalPagar(totalPagar);
                    listaResumenTrabajoVO.add(resTrabVO);
                }

                this.setListaResumenes(listaResumenTrabajoVO);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se han encontrado registros para listar", "No se han encontrado registros para listar");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            logger.error("Error grave listando resumen de trabajos", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave listando resumen de trabajos.", "Error grave listando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void actualizarPagoResumen() {
        try {
            if (this.getResumenesSeleccionados() != null && !this.getResumenesSeleccionados().isEmpty()) {
                ResumenTrabajo resTrab = null;
                for (ResumenTrabajoVO res : this.getResumenesSeleccionados()) {
                    resTrab = resumenTrabajoFacade.find((int)res.getIdResumenTrabajo());
                    resTrab.setEstado(Constantes.ESTADO_PAGADO);
                    resumenTrabajoFacade.edit(resTrab);
                }

                this.setListaResumenes(null);
                this.setResumenesSeleccionados(null);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resumenes actualizados exitosamente.", "Resumenes actualizados exitosamente.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar al menos un Trabajador de la tabla.", "Debe seleccionar al menos un Trabajador de la tabla.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            logger.error("Error grave actualizando estados de resumenes.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave actualizando resumen de trabajos.", "Error grave actualizando resumen de trabajos.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
