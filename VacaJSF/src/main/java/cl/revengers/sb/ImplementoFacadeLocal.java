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

import cl.revengers.entities.Implemento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ImplementoFacadeLocal {

    void create(Implemento implemento);

    void edit(Implemento implemento);

    void remove(Implemento implemento);

    Implemento find(Object id);

    List<Implemento> findAll();

    List<Implemento> findRange(int[] range);

    int count();
    
    public Implemento getImplementoByNombre(String nombreImplemento);
}
