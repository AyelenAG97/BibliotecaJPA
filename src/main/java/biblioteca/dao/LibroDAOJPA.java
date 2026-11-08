package biblioteca.dao;

import biblioteca.modelo.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class LibroDAOJPA implements LibroDAO {

    private final EntityManager em;

    public LibroDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Libro libro) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(libro);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo insertar el libro", e);
        }
    }

    @Override
    public Libro findById(Long id) throws DaoException {
        try {
            return em.find(Libro.class, id);
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar el libro con id " + id, e);
        }
    }

    @Override
    public List<Libro> findAll() throws DaoException {
        try {
            TypedQuery<Libro> query = em.createQuery(
                    "SELECT l FROM Libro l", Libro.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo listar los libros", e);
        }
    }

    @Override
    public void update(Libro libro) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(libro);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo actualizar el libro con id " + libro.getId(), e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        EntityTransaction tx = em.getTransaction();
        try {
            Libro libro = em.find(Libro.class, id);
            if (libro == null) {
                throw new DaoException("No existe el libro con id " + id);
            }
            tx.begin();
            em.remove(libro);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DaoException("No se pudo eliminar el libro con id " + id, e);
        }
    }

    @Override
    public List<Libro> findByCategoria(String categoria) throws DaoException {
        try {
            TypedQuery<Libro> query = em.createQuery(
                    "SELECT l FROM Libro l WHERE l.categoria = :categoria", Libro.class);
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException("No se pudo buscar libros con categoría " + categoria, e);
        }
    }
}

