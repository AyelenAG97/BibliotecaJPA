package biblioteca.dao;

import biblioteca.modelo.Libro;
import java.util.List;

public interface LibroDAO extends DAO<Libro, Long> {
    List<Libro> findByCategoria(String categoria) throws DaoException;
}

