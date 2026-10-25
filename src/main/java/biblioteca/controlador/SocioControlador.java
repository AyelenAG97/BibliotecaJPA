/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.controlador;

/**
 *
 * @author Ayelén
 */

import biblioteca.dao.DaoException;
import biblioteca.dao.SocioDAO;
import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Socio;
import biblioteca.modelo.casosdeuso.AltaSocio;
import biblioteca.modelo.casosdeuso.BajaSocio;
import biblioteca.modelo.casosdeuso.ListadoSocios;
import biblioteca.modelo.casosdeuso.ModificacionSocio;
import biblioteca.vista.SocioVista;

public class SocioControlador {

    private final SocioDAO dao;
    private final SocioVista vista;

    private final AltaSocio altaSocio = new AltaSocio();
    private final ModificacionSocio modificacionSocio = new ModificacionSocio();
    private final BajaSocio bajaSocio = new BajaSocio();
    private final ListadoSocios listadoSocios = new ListadoSocios();

    public SocioControlador(SocioDAO dao, SocioVista vista) {
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
                seleccionarSocio();
            }
        });
    }

    private void registrar() {
        try {
            altaSocio.ejecutar(dao, crearSocioDesdeFormulario());
            vista.mostrarMensaje("Socio registrado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void actualizarTabla() {
        try {
            String filtro = vista.getFiltroSeleccionado();
            vista.mostrarSocios(listadoSocios.ejecutar(dao, filtro));
        } catch (DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

     private void modificar() {
        Socio socio = crearSocioDesdeFormulario();
        socio.setId(idDe(vista.getSocioSeleccionado()));
        try {
            modificacionSocio.ejecutar(dao, socio);
            vista.mostrarMensaje("Socio modificado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void eliminar() {
        Socio seleccionado = vista.getSocioSeleccionado();
        if (seleccionado != null && !vista.confirmar("¿Eliminar al socio "
                + seleccionado.getApellido() + ", " + seleccionado.getNombre() + "?")) {
            return;
        }
        try {
            bajaSocio.ejecutar(dao, idDe(seleccionado));
            vista.mostrarMensaje("Socio eliminado correctamente.");
            limpiar();
            actualizarTabla();
        } catch (DatosInvalidosException | DaoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void seleccionarSocio() {
        Socio seleccionado = vista.getSocioSeleccionado();
        if (seleccionado != null) {
            vista.cargarFormulario(seleccionado);
        }
        vista.habilitarEdicion(seleccionado != null);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        vista.habilitarEdicion(false);
    }

    private Socio crearSocioDesdeFormulario() {
        return new Socio(vista.getNombre(), vista.getApellido(), vista.getDni(),
                vista.getEmail(), vista.getTelefono(), vista.getEstado());
    }

    private Long idDe(Socio socio) {
        return socio == null ? null : socio.getId();
    }
}