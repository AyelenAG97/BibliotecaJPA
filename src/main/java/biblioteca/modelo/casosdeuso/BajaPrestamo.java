package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.DatosInvalidosException;

public class BajaPrestamo {

    public void ejecutar(PrestamoDAO dao, Long id)
            throws DatosInvalidosException, DaoException {
        if (id == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el préstamo a eliminar.");
        }
        dao.delete(id);
    }
}

