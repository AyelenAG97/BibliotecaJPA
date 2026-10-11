package com.mycompany.bibliotecajpa;

import biblioteca.dao.DaoException;
import biblioteca.dao.SocioDAO;
import biblioteca.dao.SocioDAOJPA;
import biblioteca.modelo.Socio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BibliotecaJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();
        SocioDAO dao = new SocioDAOJPA(em);

        try {
            // 1. INSERT
            Socio ana = new Socio("Ana", "Pérez", "12345678",
                    "ana@mail.com", "1155550000", "Activo");
            Socio luis = new Socio("Luis", "Gómez", "23456789",
                    "luis@mail.com", "1155551111", "Suspendido");
            dao.insert(ana);
            dao.insert(luis);
            System.out.println("Insertados con id: " + ana.getId() + " y " + luis.getId());

            // 2. FIND ALL
            System.out.println("\n--- Todos los socios ---");
            for (Socio s : dao.findAll()) {
                mostrar(s);
            }

            // 3. FIND BY ID
            System.out.println("\n--- Buscar por id " + ana.getId() + " ---");
            mostrar(dao.findById(ana.getId()));

            // 4. UPDATE
            ana.setEstado("Suspendido");
            dao.update(ana);
            System.out.println("\nAna pasó a Suspendido");

            // 5. FIND BY ESTADO
            System.out.println("\n--- Socios suspendidos ---");
            for (Socio s : dao.findByEstado("Suspendido")) {
                mostrar(s);
            }

            // 6. DELETE
            dao.delete(luis.getId());
            System.out.println("\nLuis eliminado. Quedan: " + dao.findAll().size() + " socio(s)");

            // 7. DELETE de un id inexistente: debe lanzar DaoException
            dao.delete(9999L);

        } catch (DaoException e) {
            System.err.println("\nError del DAO: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrar(Socio s) {
        System.out.println(s.getId() + " | " + s.getDni() + " - "
                + s.getApellido() + ", " + s.getNombre() + " (" + s.getEstado() + ")");
    }
}