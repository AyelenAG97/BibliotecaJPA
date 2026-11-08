package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Prestamo;

public class AltaPrestamo {

    public void ejecutar(PrestamoDAO dao, Prestamo prestamo)
            throws DatosInvalidosException, DaoException {
        ValidadorPrestamo.validar(prestamo);
        dao.insert(prestamo);
    }
}

