package biblioteca.vista;

import biblioteca.modelo.Libro;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Socio;
import biblioteca.modelo.casosdeuso.ListadoPrestamos;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;

/**
 * Ventana gráfica para el alta, baja, modificación y listado de préstamos.
 */
public class PrestamoVista extends JFrame {

    // Formulario de datos del préstamo
    private final JLabel lblSocio = new JLabel("Socio:");
    private final JComboBox<Socio> comboSocio = new JComboBox<>();

    private final JLabel lblLibro = new JLabel("Libro:");
    private final JComboBox<Libro> comboLibro = new JComboBox<>();

    private final JLabel lblFechaPrestamo = new JLabel("Fecha Préstamo (AAAA-MM-DD):");
    private final JTextField txtFechaPrestamo = new JTextField(LocalDate.now().toString(), 12);

    private final JLabel lblFechaDevolucion = new JLabel("Fecha Devolución (AAAA-MM-DD):");
    private final JTextField txtFechaDevolucion = new JTextField(12);

    private final JLabel lblEstado = new JLabel("Estado:");
    private final JComboBox<String> comboEstado = new JComboBox<>(Prestamo.ESTADOS);

    // Botones del CRUD
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    // Listado de préstamos
    private final JComboBox<String> comboFiltro = new JComboBox<>();
    private final PrestamoTableModel modeloTabla = new PrestamoTableModel();
    private final JTable tabla = new JTable(modeloTabla);

    public PrestamoVista() {
        super("Gestión de Préstamos - Biblioteca");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(760, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        cargarOpcionesDeFiltro();
        configurarTabla();
        habilitarEdicion(false);
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);
    }

    private void cargarOpcionesDeFiltro() {
        comboFiltro.addItem(ListadoPrestamos.FILTRO_TODOS);
        for (String estado : Prestamo.ESTADOS) {
            comboFiltro.addItem(estado);
        }
    }

    private void configurarTabla() {
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
    }

    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(crearPanelFormulario(), BorderLayout.CENTER);
        panelSuperior.add(crearPanelBotones(), BorderLayout.SOUTH);
        return panelSuperior;
    }

    private JPanel crearPanelFormulario() {
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del préstamo"));
        panelFormulario.add(lblSocio);
        panelFormulario.add(comboSocio);
        panelFormulario.add(lblLibro);
        panelFormulario.add(comboLibro);
        panelFormulario.add(lblFechaPrestamo);
        panelFormulario.add(txtFechaPrestamo);
        panelFormulario.add(lblFechaDevolucion);
        panelFormulario.add(txtFechaDevolucion);
        panelFormulario.add(lblEstado);
        panelFormulario.add(comboEstado);
        return panelFormulario;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnRegistrar);
        return panelBotones;
    }

    private JPanel crearPanelTabla() {
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtrar por estado:"));
        panelFiltro.add(comboFiltro);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Préstamos registrados"));
        panelTabla.add(panelFiltro, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panelTabla;
    }

    // ---- Lectura de datos del formulario (la usa el controlador) ----

    public Socio getSocio() { return (Socio) comboSocio.getSelectedItem(); }
    public Libro getLibro() { return (Libro) comboLibro.getSelectedItem(); }
    public String getFechaPrestamoTexto() { return txtFechaPrestamo.getText().trim(); }
    public String getFechaDevolucionTexto() { return txtFechaDevolucion.getText().trim(); }
    public String getEstado() { return (String) comboEstado.getSelectedItem(); }
    public String getFiltroSeleccionado() { return (String) comboFiltro.getSelectedItem(); }

    public Prestamo getPrestamoSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return modeloTabla.getPrestamo(fila);
    }

    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox<String> getComboFiltro() { return comboFiltro; }
    public JTable getTabla() { return tabla; }

    public void cargarSocios(List<Socio> socios) {
        comboSocio.removeAllItems();
        for (Socio s : socios) {
            comboSocio.addItem(s);
        }
    }

    public void cargarLibros(List<Libro> libros) {
        comboLibro.removeAllItems();
        for (Libro l : libros) {
            comboLibro.addItem(l);
        }
    }

    public void mostrarPrestamos(List<Prestamo> prestamos) {
        modeloTabla.actualizar(prestamos);
    }

    public void cargarFormulario(Prestamo prestamo) {
        comboSocio.setSelectedItem(prestamo.getSocio());
        comboLibro.setSelectedItem(prestamo.getLibro());
        txtFechaPrestamo.setText(prestamo.getFechaPrestamo() != null
                ? prestamo.getFechaPrestamo().toString() : "");
        txtFechaDevolucion.setText(prestamo.getFechaDevolucion() != null
                ? prestamo.getFechaDevolucion().toString() : "");
        comboEstado.setSelectedItem(prestamo.getEstado());
    }

    public void limpiarFormulario() {
        if (comboSocio.getItemCount() > 0) {
            comboSocio.setSelectedIndex(0);
        }
        if (comboLibro.getItemCount() > 0) {
            comboLibro.setSelectedIndex(0);
        }
        txtFechaPrestamo.setText(LocalDate.now().toString());
        txtFechaDevolucion.setText("");
        comboEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }

    public void habilitarEdicion(boolean hayPrestamoSeleccionado) {
        btnRegistrar.setEnabled(!hayPrestamoSeleccionado);
        btnModificar.setEnabled(hayPrestamoSeleccionado);
        btnEliminar.setEnabled(hayPrestamoSeleccionado);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String pregunta) {
        int respuesta = JOptionPane.showConfirmDialog(this, pregunta, "Confirmar",
                JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
