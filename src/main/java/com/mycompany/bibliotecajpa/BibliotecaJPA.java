package com.mycompany.bibliotecajpa;

import biblioteca.controlador.LibroControlador;
import biblioteca.controlador.PrestamoControlador;
import biblioteca.controlador.SocioControlador;
import biblioteca.dao.LibroDAO;
import biblioteca.dao.LibroDAOJPA;
import biblioteca.dao.PrestamoDAO;
import biblioteca.dao.PrestamoDAOJPA;
import biblioteca.dao.SocioDAO;
import biblioteca.dao.SocioDAOJPA;
import biblioteca.vista.LibroVista;
import biblioteca.vista.PrestamoVista;
import biblioteca.vista.SocioVista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la conexión JPA y despliega el menú de la aplicación.
 */
public class BibliotecaJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();

        SocioDAO socioDao = new SocioDAOJPA(em);
        LibroDAO libroDao = new LibroDAOJPA(em);
        PrestamoDAO prestamoDao = new PrestamoDAOJPA(em);

        SwingUtilities.invokeLater(() -> {
            SocioVista socioVista = new SocioVista();
            new SocioControlador(socioDao, socioVista);

            LibroVista libroVista = new LibroVista();
            new LibroControlador(libroDao, libroVista);

            PrestamoVista prestamoVista = new PrestamoVista();
            PrestamoControlador prestamoControlador = new PrestamoControlador(
                    prestamoDao, socioDao, libroDao, prestamoVista);

            JFrame menu = new JFrame("Biblioteca JPA - Menú Principal");
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setSize(340, 220);
            menu.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JButton btnSocios = new JButton("Gestión de Socios");
            JButton btnLibros = new JButton("Gestión de Libros");
            JButton btnPrestamos = new JButton("Gestión de Préstamos");

            btnSocios.addActionListener(e -> socioVista.setVisible(true));
            btnLibros.addActionListener(e -> libroVista.setVisible(true));
            btnPrestamos.addActionListener(e -> {
                prestamoControlador.cargarCombos();
                prestamoControlador.actualizarTabla();
                prestamoVista.setVisible(true);
            });

            panel.add(btnSocios);
            panel.add(btnLibros);
            panel.add(btnPrestamos);

            menu.add(panel);
            menu.setVisible(true);
        });
    }
}
