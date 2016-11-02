/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.ResumenProductos;
import cl.revengers.entities.ResumenTrabajo;
import cl.revengers.utils.Constantes;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class ResumenTrabajoFacade extends AbstractFacade<ResumenTrabajo> implements ResumenTrabajoFacadeLocal {

    private final static Logger logger = Logger.getLogger(ResumenTrabajoFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResumenTrabajoFacade() {
        super(ResumenTrabajo.class);
    }

    @Override
    public List<ResumenTrabajo> obtenerResumenesPorTrabajador(int rutTrabajador) {
        Query query = null;
        try {
            query = em.createQuery("SELECT r FROM ResumenTrabajo r JOIN r.idTrabajador t WHERE r.estado = :estado AND t.rut = :rut", ResumenTrabajo.class);
            query.setParameter("estado", Constantes.ESTADO_NO_PAGADO);
            query.setParameter("rut", rutTrabajador);
            List<ResumenTrabajo> lista = (List<ResumenTrabajo>) query.getResultList();
           
            return lista;
        } catch (Exception e) {
            logger.error("Error grave obteniendo resumenes por trabajador.");
            throw new RuntimeException(e);
        } finally {
            query = null;
        }
    }

}
