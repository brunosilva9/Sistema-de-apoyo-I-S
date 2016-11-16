package cl.revengers.sb;

import cl.revengers.entities.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {
    
    private final static Logger logger = Logger.getLogger(ImplementoFacade.class);

    @PersistenceContext(unitName = "cl.duoc_VacaJSF_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    @Override
    public Producto getProductoByNombre(String nombreProducto) {
        Query query = null;
        try{
            query = em.createQuery("SELECT t FROM Producto t WHERE t.nombre = :nombre", Producto.class);
            query.setParameter("nombre", nombreProducto);
            Producto trab = (Producto) query.getSingleResult();
            return trab;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            logger.error("Error grave obteniendo Implemento para nombre: " + nombreProducto, e);
            throw new RuntimeException(e);
        }
    }

    
    
    
    
}
