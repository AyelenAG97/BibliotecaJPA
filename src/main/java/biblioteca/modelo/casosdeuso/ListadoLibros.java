package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.modelo.Libro;
import java.util.List;

public class ListadoLibros {

    public static final String FILTRO_TODOS = "Todos";

    public List<Libro> ejecutar(LibroDAO dao, String filtroCategoria) throws DaoException {
        if (filtroCategoria == null || FILTRO_TODOS.equals(filtroCategoria)) {
            return dao.findAll();
        }
        return dao.findByCategoria(filtroCategoria);
    }
}

