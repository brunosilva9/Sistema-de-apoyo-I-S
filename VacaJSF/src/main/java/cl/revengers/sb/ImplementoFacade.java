/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Implemento;
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
public class ImplementoFacade extends AbstractFacade<Implemento> implements ImplementoFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(ImplementoFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImplementoFacade() {
        super(Implemento.class);
    }

    @Override
    public Implemento getImplementoByNombre(String nombreImplemento) {
        Query query = null;
        try{
            query = em.createQuery("SELECT t FROM Implemento t WHERE t.nombre = :nombre", Implemento.class);
            query.setParameter("nombre", nombreImplemento);
            Implemento trab = (Implemento) query.getSingleResult();
            return trab;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo Implemento para nombre: " + nombreImplemento, e);
            throw new RuntimeException(e);
        }
    }

    
    
    
    
}
