package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Libro;

public class AltaLibro {

    public void ejecutar(LibroDAO dao, Libro libro)
            throws DatosInvalidosException, DaoException {
        ValidadorLibro.validar(libro);
        dao.insert(libro);
    }
}

