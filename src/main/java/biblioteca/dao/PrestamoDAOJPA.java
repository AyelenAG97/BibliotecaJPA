package biblioteca.dao;

import biblioteca.modelo.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PrestamoDAOJPA implements PrestamoDAO {

    private final EntityManager em;

    public PrestamoDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Prestamo prestamo) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(prestamo);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo insertar el préstamo", e);
        }
    }

    @Override
    public Prestamo findById(Long id) throws DaoException {
        try {
            return em.find(Prestamo.class, id);
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar el préstamo con id " + id, e);
        }
    }

    @Override
    public List<Prestamo> findAll() throws DaoException {
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                    "SELECT p FROM Prestamo p", Prestamo.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo listar los préstamos", e);
        }
    }

    @Override
    public void update(Prestamo prestamo) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(prestamo);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo actualizar el préstamo con id " + prestamo.getId(), e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            Prestamo prestamo = em.find(Prestamo.class, id);
            if (prestamo == null) {
                throw new DaoException("No existe el préstamo con id " + id);
            }
            tx.begin();
            em.remove(prestamo);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo eliminar el préstamo con id " + id, e);
        }
    }

    @Override
    public List<Prestamo> findByEstado(String estado) throws DaoException {
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                    "SELECT p FROM Prestamo p WHERE p.estado = :estado", Prestamo.class);
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar préstamos con estado " + estado, e);
        }
    }
}

