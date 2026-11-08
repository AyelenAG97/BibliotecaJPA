package biblioteca.controlador;

import biblioteca.dao.DaoException;
import biblioteca.dao.LibroDAO;
import biblioteca.dao.PrestamoDAO;
import biblioteca.dao.SocioDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.casosdeuso.AltaPrestamo;
import biblioteca.modelo.casosdeuso.BajaPrestamo;
import biblioteca.modelo.casosdeuso.ListadoPrestamos;
import biblioteca.modelo.casosdeuso.ModificacionPrestamo;
import biblioteca.vista.PrestamoVista;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PrestamoControlador {

    private final PrestamoDAO prestamoDao;
    private final SocioDAO socioDao;
    private final LibroDAO libroDao;
    private final PrestamoVista vista;

    private final AltaPrestamo altaPrestamo = new AltaPrestamo();
    private final ModificacionPrestamo modificacionPrestamo = new ModificacionPrestamo();
    private final BajaPrestamo bajaPrestamo = new BajaPrestamo();
    private final ListadoPrestamos listadoPrestamos = new ListadoPrestamos();

    public PrestamoControlador(PrestamoDAO prestamoDao, SocioDAO socioDao,
                               LibroDAO libroDao, PrestamoVista vista) {
        this.prestamoDao = prestamoDao;
        this.socioDao = socioDao;
        this.libroDao = libroDao;
        this.vista = vista;
        registrarEventos();
        cargarCombos();
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
                seleccionarPrestamo();
            }
        });
    }

    public final void cargarCombos() {
        try {
            vista.cargarSocios(socioDao.findAll());
            vista.cargarLibros(libroDao.findAll());
        } catch (DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void registrar() {
        try {
            altaPrestamo.ejecutar(prestamoDao, crearPrestamoDesdeFormulario());
            vista.mostrarMensaje("Préstamo registrado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    public final void actualizarTabla() {
        try {
            String filtro = vista.getFiltroSeleccionado();
            vista.mostrarPrestamos(listadoPrestamos.ejecutar(prestamoDao, filtro));
        } catch (DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void modificar() {
        try {
            Prestamo prestamo = crearPrestamoDesdeFormulario();
            prestamo.setId(idDe(vista.getPrestamoSeleccionado()));
            modificacionPrestamo.ejecutar(prestamoDao, prestamo);
            vista.mostrarMensaje("Préstamo modificado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void eliminar() {
        Prestamo seleccionado = vista.getPrestamoSeleccionado();
        if (seleccionado != null && !vista.confirmar("¿Eliminar el préstamo #"
                + seleccionado.getId() + "?")) {
            return;
        }
        try {
            bajaPrestamo.ejecutar(prestamoDao, idDe(seleccionado));
            vista.mostrarMensaje("Préstamo eliminado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void seleccionarPrestamo() {
        Prestamo seleccionado = vista.getPrestamoSeleccionado();
        if (seleccionado != null) {
            vista.cargarFormulario(seleccionado);
        }
        vista.habilitarEdicion(seleccionado != null);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        vista.habilitarEdicion(false);
    }

    private Prestamo crearPrestamoDesdeFormulario() throws DatosInvalidosException {
        LocalDate fechaPrestamo = parsearFecha(vista.getFechaPrestamoTexto(), "Fecha de préstamo");
        String textoDevolucion = vista.getFechaDevolucionTexto();
        LocalDate fechaDevolucion = textoDevolucion.isEmpty()
                ? null
                : parsearFecha(textoDevolucion, "Fecha de devolución");
        return new Prestamo(vista.getSocio(), vista.getLibro(),
                fechaPrestamo, fechaDevolucion, vista.getEstado());
    }

    private LocalDate parsearFecha(String texto, String campo) throws DatosInvalidosException {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(texto);
        } catch (DateTimeParseException e) {
            throw new DatosInvalidosException(
                    campo + " inválida. Usá el formato AAAA-MM-DD (ej: 2026-11-08).");
        }
    }

    private Long idDe(Prestamo prestamo) {
        return prestamo == null ? null : prestamo.getId();
    }
}

