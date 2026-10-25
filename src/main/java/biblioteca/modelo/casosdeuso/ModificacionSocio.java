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
import biblioteca.modelo.Socio;

public class ModificacionSocio {

    public void ejecutar(SocioDAO dao, Socio socio)
            throws DatosInvalidosException, DaoException {
        if (socio.getId() == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el socio a modificar.");
        }
        ValidadorSocio.validar(socio);
        dao.update(socio);
    }
}
