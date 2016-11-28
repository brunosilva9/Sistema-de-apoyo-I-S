/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Faena;
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
public class FaenaFacade extends AbstractFacade<Faena> implements FaenaFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(FaenaFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaenaFacade() {
        super(Faena.class);
    }

    @Override
    public Faena getFaenaById(int idFaena) {
        Query query = null;
        try{
            query = em.createQuery("SELECT t FROM Faena t WHERE t.idFaena = :idFaena", Faena.class);
            query.setParameter("idFaena", idFaena);
            Faena trab = (Faena) query.getSingleResult();
            return trab;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo faena para id: " + idFaena, e);
            throw new RuntimeException(e);
        }
    }
    
    
    
}
