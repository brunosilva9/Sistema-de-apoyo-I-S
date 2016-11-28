/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.Faena;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface FaenaFacadeLocal {

    void create(Faena faena);

    void edit(Faena faena);

    void remove(Faena faena);

    Faena find(Object id);

    List<Faena> findAll();

    List<Faena> findRange(int[] range);

    int count();
    
    public Faena getFaenaById(int idFaena);
}
