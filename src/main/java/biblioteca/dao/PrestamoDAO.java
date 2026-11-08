package biblioteca.dao;

import biblioteca.modelo.Prestamo;
import java.util.List;

public interface PrestamoDAO extends DAO<Prestamo, Long> {
    List<Prestamo> findByEstado(String estado) throws DaoException;
}

