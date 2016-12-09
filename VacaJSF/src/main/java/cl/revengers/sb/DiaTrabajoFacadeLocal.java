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

import cl.revengers.entities.DiaTrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface DiaTrabajoFacadeLocal {

    void create(DiaTrabajo diaTrabajo);

    void edit(DiaTrabajo diaTrabajo);

    void remove(DiaTrabajo diaTrabajo);

    DiaTrabajo find(Object id);

    List<DiaTrabajo> findAll();

    List<DiaTrabajo> findRange(int[] range);

    int count();
    
    
}
