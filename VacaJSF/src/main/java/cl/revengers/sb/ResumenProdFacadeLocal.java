/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.ResumenProductos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ResumenProdFacadeLocal {

    void create(ResumenProductos resumenProd);

    void edit(ResumenProductos resumenProd);

    void remove(ResumenProductos resumenProd);

    ResumenProductos find(Object id);

    List<ResumenProductos> findAll();

    List<ResumenProductos> findRange(int[] range);

    int count();
 
}
