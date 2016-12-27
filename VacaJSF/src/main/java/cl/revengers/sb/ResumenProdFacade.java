/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.ResumenProductos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Esteban Perez
 */
@Stateless
public class ResumenProdFacade extends AbstractFacade<ResumenProductos> implements ResumenProdFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(ResumenProdFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    public ResumenProdFacade() {
        super(ResumenProductos.class);
    }
    
       @Override
        public List <ResumenProductos> obtenerResrPorDia(int idDia) {
        Query query = null;
        try {
            query = em.createQuery("SELECT r FROM ResumenProductos r JOIN ResumenTrabajo t JOIN DiaTrabajo d WHERE d.idDia = :idDia AND d.idDia = t.idDia.idDia AND t.idResumen = r.idResumen.idResumen GROUP BY r.idResProd ", ResumenProductos.class);
            
            query.setParameter("idDia", idDia);
            List<ResumenProductos> lista = (List<ResumenProductos>) query.getResultList();
           
            return lista;
        } catch (Exception e) {
            logger.error("Error grave obteniendo resumenes por trabajador.");
            throw new RuntimeException(e);
        } finally {
            query = null;
        }
    }

    
  
    
    
    
}