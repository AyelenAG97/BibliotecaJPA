/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package biblioteca.dao;

/**
 *
 * @author Ayelén
 */
import java.util.List;

/**
 * Interfaz genérica con las operaciones CRUD básicas de persistencia.
 *
 * @param <T> Tipo de la entidad.
 * @param <ID> Tipo del identificador de la entidad.
 */
public interface DAO<T, ID> {
    void insert(T entity) throws DaoException;
    T findById(ID id) throws DaoException;
    List<T> findAll() throws DaoException;
    void update(T entity) throws DaoException;
    void delete(ID id) throws DaoException;
}
