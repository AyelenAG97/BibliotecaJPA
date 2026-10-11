package biblioteca.dao;

import biblioteca.modelo.Socio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SocioDAOJPA implements SocioDAO {

    private final EntityManager em;

    public SocioDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Socio socio) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(socio);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo insertar el socio", e);
        }
    }

    @Override
    public Socio findById(Long id) throws DaoException {
        try {
            return em.find(Socio.class, id);
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar el socio con id " + id, e);
        }
    }

    @Override
    public List<Socio> findAll() throws DaoException {
        try {
            TypedQuery<Socio> query = em.createQuery(
                    "SELECT s FROM Socio s", Socio.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo listar los socios", e);
        }
    }

    @Override
    public void update(Socio socio) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(socio);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo actualizar el socio con id " + socio.getId(), e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            Socio socio = em.find(Socio.class, id);
            if (socio == null) {
                throw new DaoException("No existe el socio con id " + id);
            }
            tx.begin();
            em.remove(socio);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo eliminar el socio con id " + id, e);
        }
    }

    @Override
    public List<Socio> findByEstado(String estado) throws DaoException {
        try {
            TypedQuery<Socio> query = em.createQuery(
                    "SELECT s FROM Socio s WHERE s.estado = :estado", Socio.class);
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar socios con estado " + estado, e);
        }
    }
}