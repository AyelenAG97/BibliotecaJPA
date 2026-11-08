package biblioteca.controlador;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Libro;
import biblioteca.modelo.casosdeuso.AltaLibro;
import biblioteca.modelo.casosdeuso.BajaLibro;
import biblioteca.modelo.casosdeuso.ListadoLibros;
import biblioteca.modelo.casosdeuso.ModificacionLibro;
import biblioteca.vista.LibroVista;

public class LibroControlador {

    private final LibroDAO dao;
    private final LibroVista vista;

    private final AltaLibro altaLibro = new AltaLibro();
    private final ModificacionLibro modificacionLibro = new ModificacionLibro();
    private final BajaLibro bajaLibro = new BajaLibro();
    private final ListadoLibros listadoLibros = new ListadoLibros();

    public LibroControlador(LibroDAO dao, LibroVista vista) {
        this.dao = dao;
        this.vista = vista;
        registrarEventos();
        actualizarTabla();
    }

    private void registrarEventos() {
        vista.getBtnRegistrar().addActionListener(e -> registrar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnLimpiar().addActionListener(e -> limpiar());
        vista.getComboFiltro().addActionListener(e -> actualizarTabla());
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarLibro();
            }
        });
    }

    private void registrar() {
        try {
            altaLibro.ejecutar(dao, crearLibroDesdeFormulario());
            vista.mostrarMensaje("Libro registrado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public void actualizarTabla() {
        try {
            String filtro = vista.getFiltroSeleccionado();
            vista.mostrarLibros(listadoLibros.ejecutar(dao, filtro));
        } catch (DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void modificar() {
        try {
            Libro libro = crearLibroDesdeFormulario();
            libro.setId(idDe(vista.getLibroSeleccionado()));
            modificacionLibro.ejecutar(dao, libro);
            vista.mostrarMensaje("Libro modificado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void eliminar() {
        Libro seleccionado = vista.getLibroSeleccionado();
        if (seleccionado != null && !vista.confirmar("¿Eliminar el libro \""
                + seleccionado.getTitulo() + "\"?")) {
            return;
        }
        try {
            bajaLibro.ejecutar(dao, idDe(seleccionado));
            vista.mostrarMensaje("Libro eliminado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void seleccionarLibro() {
        Libro seleccionado = vista.getLibroSeleccionado();
        if (seleccionado != null) {
            vista.cargarFormulario(seleccionado);
        }
        vista.habilitarEdicion(seleccionado != null);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        vista.habilitarEdicion(false);
    }

    private Libro crearLibroDesdeFormulario() throws DatosInvalidosException {
        int cantidad;
        try {
            cantidad = Integer.parseInt(vista.getCantidadTexto());
        } catch (NumberFormatException e) {
            throw new DatosInvalidosException("La cantidad de ejemplares debe ser un número entero.");
        }
        return new Libro(vista.getTitulo(), vista.getAutor(), vista.getEditorial(),
                vista.getCategoria(), cantidad);
    }

    private Long idDe(Libro libro) {
        return libro == null ? null : libro.getId();
    }
}

