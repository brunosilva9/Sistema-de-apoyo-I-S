package cl.revengers.sb;

import cl.revengers.entities.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ProductoFacadeLocal {

    void create(Producto producto);

    void edit(Producto producto);

    void remove(Producto producto);

    Producto find(Object id);

    List<Producto> findAll();

    List<Producto> findRange(int[] range);

    int count();
    
    public Producto getProductoByNombre(String nombreProducto);
}
