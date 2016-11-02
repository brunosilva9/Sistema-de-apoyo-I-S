/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.sb;

import cl.revengers.entities.ResumenTrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Esteban Perez
 */
@Local
public interface ResumenTrabajoFacadeLocal {

    void create(ResumenTrabajo resumenTrabajo);

    void edit(ResumenTrabajo resumenTrabajo);

    void remove(ResumenTrabajo resumenTrabajo);

    ResumenTrabajo find(Object id);

    List<ResumenTrabajo> findAll();

    List<ResumenTrabajo> findRange(int[] range);

    int count();
 
    public List<ResumenTrabajo> obtenerResumenesPorTrabajador(int rutTrabajador);
}
