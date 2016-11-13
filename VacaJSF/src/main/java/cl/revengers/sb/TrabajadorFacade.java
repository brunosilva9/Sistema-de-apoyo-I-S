/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Trabajador;
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
public class TrabajadorFacade extends AbstractFacade<Trabajador> implements TrabajadorFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(TrabajadorFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrabajadorFacade() {
        super(Trabajador.class);
    }

    @Override
    public Trabajador getTrabajadorByRut(int rutTrabajador) {
        Query query = null;
        try{
            query = em.createQuery("SELECT t FROM Trabajador t WHERE t.rut = :rut", Trabajador.class);
            query.setParameter("rut", rutTrabajador);
            Trabajador trab = (Trabajador) query.getSingleResult();
            return trab;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo trabajador para Rut: " + rutTrabajador, e);
            throw new RuntimeException(e);
        }
    }
    
    
    
}
