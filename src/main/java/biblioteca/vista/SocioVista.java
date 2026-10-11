package biblioteca.vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ayelén
 */
public class SocioVista extends JFrame {

    // Datos para dar de alta un socio
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JTextField txtNombre = new JTextField(20);

    private final JLabel lblApellido = new JLabel("Apellido:");
    private final JTextField txtApellido = new JTextField(20);

    private final JLabel lblDni = new JLabel("DNI:");
    private final JTextField txtDni = new JTextField(10);

    private final JLabel lblEmail = new JLabel("Email:");
    private final JTextField txtEmail = new JTextField(20);

    private final JLabel lblTelefono = new JLabel("Teléfono:");
    private final JTextField txtTelefono = new JTextField(15);

    private final JLabel lblEstado = new JLabel("Estado:");
    private final JComboBox<String> comboEstado = new JComboBox<>();

    private final JButton btnRegistrar = new JButton("Registrar Socio");
    private final JButton btnLimpiar = new JButton("Limpiar");

    private final DefaultListModel<String> modeloListaSocios = new DefaultListModel<>();
    private final JList<String> listaSocios = new JList<>(modeloListaSocios);

    public SocioVista() {
        super("Gestión de Socios - Biblioteca");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 430);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        cargarDatosDeEjemplo();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelLista(), BorderLayout.CENTER);
    }

    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(crearPanelFormulario(), BorderLayout.CENTER);
        panelSuperior.add(crearPanelBotones(), BorderLayout.SOUTH);
        return panelSuperior;
    }

    private JPanel crearPanelFormulario() {
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Nuevo socio"));
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
        panelBotones.add(btnRegistrar);
        return panelBotones;
    }

    private JScrollPane crearPanelLista() {
        listaSocios.setVisibleRowCount(8);
        JScrollPane scrollLista = new JScrollPane(listaSocios);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Socios registrados"));
        return scrollLista;
    }

    private void cargarDatosDeEjemplo() {
        comboEstado.addItem("Activo");
        comboEstado.addItem("Suspendido");
        comboEstado.addItem("Inactivo");

        // Simulan socios ya registrados en el sistema
        modeloListaSocios.addElement("12.345.678 - Pérez, Ana (Activo)");
        modeloListaSocios.addElement("23.456.789 - Gómez, Luis (Activo)");
        modeloListaSocios.addElement("34.567.890 - Fernández, Marta (Suspendido)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SocioVista().setVisible(true));
    }
}