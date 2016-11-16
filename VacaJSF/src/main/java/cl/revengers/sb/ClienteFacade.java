/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(ClienteFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public Cliente getClienteByRut(int rutCliente) {
        Query query = null;
        try{
            query = em.createQuery("SELECT t FROM Cliente t WHERE t.rutCliente = :rutCliente", Cliente.class);
            query.setParameter("rutCliente", rutCliente);
            Cliente trab = (Cliente) query.getSingleResult();
            return trab;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo Implemento para nombre: " + rutCliente, e);
            throw new RuntimeException(e);
        }
    }

    
    
    
    
}
