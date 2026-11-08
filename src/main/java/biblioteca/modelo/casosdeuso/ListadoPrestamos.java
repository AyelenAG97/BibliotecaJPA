package biblioteca.modelo.casosdeuso;

import biblioteca.dao.DaoException;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.Prestamo;
import java.util.List;

public class ListadoPrestamos {

    public static final String FILTRO_TODOS = "Todos";

    public List<Prestamo> ejecutar(PrestamoDAO dao, String filtroEstado) throws DaoException {
        if (filtroEstado == null || FILTRO_TODOS.equals(filtroEstado)) {
            return dao.findAll();
        }
        return dao.findByEstado(filtroEstado);
    }
}

