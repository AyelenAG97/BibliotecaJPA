/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.modelo.casosdeuso;

/**
 *
 * @author Ayelén
 */

import biblioteca.dao.DaoException;
import biblioteca.dao.SocioDAO;
import biblioteca.modelo.DatosInvalidosException;

public class BajaSocio {

    public void ejecutar(SocioDAO dao, Long id)
            throws DatosInvalidosException, DaoException {
        if (id == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el socio a eliminar.");
        }
        dao.delete(id);
    }
}