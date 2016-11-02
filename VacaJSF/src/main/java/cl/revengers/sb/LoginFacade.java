/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Login;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author cristopherandres
 */
@Stateless 
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(LoginFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }

    @Override
    public Login getLogin(String username, String password) {
         try{
            Query query = em.createQuery("SELECT l FROM Login l WHERE l.username = :username AND l.pass = :password", Login.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Login log = (Login) query.getSingleResult();
            return log;
        }catch(NoResultException ex){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo login por rut.", e);
            throw new RuntimeException(e);
        }
    }
    
}
