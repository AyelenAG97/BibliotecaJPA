package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Libro;

public class ModificacionLibro {

    public void ejecutar(LibroDAO dao, Libro libro)
            throws DatosInvalidosException, DaoException {
        if (libro.getId() == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el libro a modificar.");
        }
        ValidadorLibro.validar(libro);
        dao.update(libro);
    }
}

