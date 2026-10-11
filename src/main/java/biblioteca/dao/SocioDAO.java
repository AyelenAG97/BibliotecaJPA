package biblioteca.dao;

import biblioteca.modelo.Socio;
import java.util.List;

public interface SocioDAO extends DAO<Socio, Long> {
    List<Socio> findByEstado(String estado) throws DaoException;
}