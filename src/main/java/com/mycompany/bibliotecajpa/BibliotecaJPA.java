package com.mycompany.bibliotecajpa;

import biblioteca.controlador.SocioControlador;
import biblioteca.dao.SocioDAO;
import biblioteca.dao.SocioDAOJPA;
import biblioteca.vista.SocioVista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.SwingUtilities;

public class BibliotecaJPA {

    public static void main(String[] args) {
  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();
        SocioDAO dao = new SocioDAOJPA(em);

        SwingUtilities.invokeLater(() -> {
            SocioVista vista = new SocioVista();
            new SocioControlador(dao, vista);
            vista.setVisible(true);
        });
    }
}