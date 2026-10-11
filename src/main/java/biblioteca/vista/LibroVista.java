package biblioteca.vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ayelén
 */
public class LibroVista extends JFrame {

    // Datos para dar de alta un libro
    private final JLabel lblTitulo = new JLabel("Título:");
    private final JTextField txtTitulo = new JTextField(20);

    private final JLabel lblAutor = new JLabel("Autor:");
    private final JTextField txtAutor = new JTextField(20);

    private final JLabel lblEditorial = new JLabel("Editorial:");
    private final JComboBox<String> comboEditorial = new JComboBox<>();

    private final JLabel lblCategoria = new JLabel("Categoría:");
    private final JComboBox<String> comboCategoria = new JComboBox<>();

    private final JLabel lblCantidad = new JLabel("Cantidad de ejemplares:");
    private final JTextField txtCantidad = new JTextField(5);

    private final JButton btnRegistrar = new JButton("Registrar Libro");
    private final JButton btnLimpiar = new JButton("Limpiar");

    // Listado de libros registrados
    private final DefaultListModel<String> modeloListaLibros = new DefaultListModel<>();
    private final JList<String> listaLibros = new JList<>(modeloListaLibros);

    public LibroVista() {
        super("Gestión de Libros - Biblioteca");
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
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Nuevo libro"));
        panelFormulario.add(lblTitulo);
        panelFormulario.add(txtTitulo);
        panelFormulario.add(lblAutor);
        panelFormulario.add(txtAutor);
        panelFormulario.add(lblEditorial);
        panelFormulario.add(comboEditorial);
        panelFormulario.add(lblCategoria);
        panelFormulario.add(comboCategoria);
        panelFormulario.add(lblCantidad);
        panelFormulario.add(txtCantidad);
        return panelFormulario;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);
        return panelBotones;
    }

    private JScrollPane crearPanelLista() {
        listaLibros.setVisibleRowCount(8);
        JScrollPane scrollLista = new JScrollPane(listaLibros);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Libros registrados"));
        return scrollLista;
    }

    private void cargarDatosDeEjemplo() {
        
        comboEditorial.addItem("Planeta");
        comboEditorial.addItem("Sudamericana");
        comboEditorial.addItem("Paidós");

        comboCategoria.addItem("Novela");
        comboCategoria.addItem("Ciencia Ficción");
        comboCategoria.addItem("Técnico");

        modeloListaLibros.addElement("Cien años de soledad - G. García Márquez (Disp: 3)");
        modeloListaLibros.addElement("Fundación - Isaac Asimov (Disp: 1)");
        modeloListaLibros.addElement("Clean Code - Robert C. Martin (Disp: 0)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibroVista().setVisible(true));
    }
}