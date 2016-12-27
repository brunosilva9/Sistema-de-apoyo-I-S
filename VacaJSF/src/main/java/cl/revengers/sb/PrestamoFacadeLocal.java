/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Prestamo;
import java.util.List;
import javax.ejb.Local;

/**
 *

 */
@Local
public interface PrestamoFacadeLocal {

    void create(Prestamo implemento);

    void edit(Prestamo implemento);

    void remove(Prestamo implemento);

    Prestamo find(Object id);

    List<Prestamo> findAll();

    List<Prestamo> findRange(int[] range);

    int count();
    
   
}
