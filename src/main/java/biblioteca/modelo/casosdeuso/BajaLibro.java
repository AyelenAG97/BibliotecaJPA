package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.modelo.DatosInvalidosException;

public class BajaLibro {

    public void ejecutar(LibroDAO dao, Long id)
            throws DatosInvalidosException, DaoException {
        if (id == null) {
            throw new DatosInvalidosException("Seleccioná en la tabla el libro a eliminar.");
        }
        dao.delete(id);
    }
}

