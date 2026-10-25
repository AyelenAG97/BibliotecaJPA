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
import biblioteca.modelo.Socio;
import java.util.List;

public class ListadoSocios {

    public static final String FILTRO_TODOS = "Todos";

    public List<Socio> ejecutar(SocioDAO dao, String filtroEstado) throws DaoException {
        if (filtroEstado == null || FILTRO_TODOS.equals(filtroEstado)) {
            return dao.findAll();
        }
        return dao.findByEstado(filtroEstado);
    }
}
