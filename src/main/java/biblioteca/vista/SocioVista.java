package biblioteca.vista;

import biblioteca.modelo.Socio;
import biblioteca.modelo.casosdeuso.ListadoSocios;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ayelén
 */
public class SocioVista extends JFrame {

    private static final String[] ESTADOS = {"Activo", "Suspendido", "Inactivo"};

    // Formulario de datos del socio
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JTextField txtNombre = new JTextField(20);

    private final JLabel lblApellido = new JLabel("Apellido:");
    private final JTextField txtApellido = new JTextField(20);

    private final JLabel lblDni = new JLabel("DNI (sin puntos):");
    private final JTextField txtDni = new JTextField(10);

    private final JLabel lblEmail = new JLabel("Email:");
    private final JTextField txtEmail = new JTextField(20);

    private final JLabel lblTelefono = new JLabel("Teléfono:");
    private final JTextField txtTelefono = new JTextField(15);

    private final JLabel lblEstado = new JLabel("Estado:");
    private final JComboBox<String> comboEstado = new JComboBox<>(ESTADOS);

    // Botones del CRUD
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    // Listado de socios
    private final JComboBox<String> comboFiltro = new JComboBox<>();
    private final SocioTableModel modeloTabla = new SocioTableModel();
    private final JTable tabla = new JTable(modeloTabla);

    public SocioVista() {
        super("Gestión de Socios - Biblioteca");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        comboFiltro.addItem(ListadoSocios.FILTRO_TODOS);
        for (String estado : ESTADOS) {
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
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del socio"));
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);
        panelFormulario.add(lblDni);
        panelFormulario.add(txtDni);
        panelFormulario.add(lblEmail);
        panelFormulario.add(txtEmail);
        panelFormulario.add(lblTelefono);
        panelFormulario.add(txtTelefono);
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
        panelTabla.setBorder(BorderFactory.createTitledBorder("Socios registrados"));
        panelTabla.add(panelFiltro, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panelTabla;
    }

    // ---- Lectura de datos del formulario (la usa el controlador) ----

    public String getNombre() { return txtNombre.getText().trim(); }
    public String getApellido() { return txtApellido.getText().trim(); }
    public String getDni() { return txtDni.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getTelefono() { return txtTelefono.getText().trim(); }
    public String getEstado() { return (String) comboEstado.getSelectedItem(); }
    public String getFiltroSeleccionado() { return (String) comboFiltro.getSelectedItem(); }

    public Socio getSocioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return modeloTabla.getSocio(fila);
    }


    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox<String> getComboFiltro() { return comboFiltro; }
    public JTable getTabla() { return tabla; }


    public void mostrarSocios(List<Socio> socios) {
        modeloTabla.actualizar(socios);
    }

    public void cargarFormulario(Socio socio) {
        txtNombre.setText(socio.getNombre());
        txtApellido.setText(socio.getApellido());
        txtDni.setText(socio.getDni());
        txtEmail.setText(socio.getEmail());
        txtTelefono.setText(socio.getTelefono());
        comboEstado.setSelectedItem(socio.getEstado());
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        comboEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }

    public void habilitarEdicion(boolean haySocioSeleccionado) {
        btnRegistrar.setEnabled(!haySocioSeleccionado);
        btnModificar.setEnabled(haySocioSeleccionado);
        btnEliminar.setEnabled(haySocioSeleccionado);
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