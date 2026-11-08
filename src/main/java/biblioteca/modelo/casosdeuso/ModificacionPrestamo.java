package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Prestamo;

public class ModificacionPrestamo {

    public void ejecutar(PrestamoDAO dao, Prestamo prestamo)
            throws DatosInvalidosException, DaoException {
        if (prestamo.getId() == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el préstamo a modificar.");
        }
        ValidadorPrestamo.validar(prestamo);
        dao.update(prestamo);
    }
}

