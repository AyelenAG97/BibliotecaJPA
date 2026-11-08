package biblioteca.vista;

import biblioteca.modelo.Libro;
import biblioteca.modelo.casosdeuso.ListadoLibros;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ayelén
 */
public class LibroVista extends JFrame {

    // Formulario de datos del libro
    private final JLabel lblTitulo = new JLabel("Título:");
    private final JTextField txtTitulo = new JTextField(20);

    private final JLabel lblAutor = new JLabel("Autor:");
    private final JTextField txtAutor = new JTextField(20);

    private final JLabel lblEditorial = new JLabel("Editorial:");
    private final JComboBox<String> comboEditorial = new JComboBox<>(Libro.EDITORIALES);

    private final JLabel lblCategoria = new JLabel("Categoría:");
    private final JComboBox<String> comboCategoria = new JComboBox<>(Libro.CATEGORIAS);

    private final JLabel lblCantidad = new JLabel("Cantidad de ejemplares:");
    private final JTextField txtCantidad = new JTextField(5);

    // Botones del CRUD
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    // Listado de libros
    private final JComboBox<String> comboFiltro = new JComboBox<>();
    private final LibroTableModel modeloTabla = new LibroTableModel();
    private final JTable tabla = new JTable(modeloTabla);

    public LibroVista() {
        super("Gestión de Libros - Biblioteca");
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
        comboFiltro.addItem(ListadoLibros.FILTRO_TODOS);
        for (String categoria : Libro.CATEGORIAS) {
            comboFiltro.addItem(categoria);
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
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del libro"));
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
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnRegistrar);
        return panelBotones;
    }

    private JPanel crearPanelTabla() {
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtrar por categoría:"));
        panelFiltro.add(comboFiltro);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Libros registrados"));
        panelTabla.add(panelFiltro, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panelTabla;
    }

    // ---- Lectura de datos del formulario (la usa el controlador) ----

    public String getTitulo() { return txtTitulo.getText().trim(); }
    public String getAutor() { return txtAutor.getText().trim(); }
    public String getEditorial() { return (String) comboEditorial.getSelectedItem(); }
    public String getCategoria() { return (String) comboCategoria.getSelectedItem(); }
    public String getCantidadTexto() { return txtCantidad.getText().trim(); }
    public String getFiltroSeleccionado() { return (String) comboFiltro.getSelectedItem(); }

    public Libro getLibroSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return modeloTabla.getLibro(fila);
    }

    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox<String> getComboFiltro() { return comboFiltro; }
    public JTable getTabla() { return tabla; }

    public void mostrarLibros(List<Libro> libros) {
        modeloTabla.actualizar(libros);
    }

    public void cargarFormulario(Libro libro) {
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());
        comboEditorial.setSelectedItem(libro.getEditorial());
        comboCategoria.setSelectedItem(libro.getCategoria());
        txtCantidad.setText(String.valueOf(libro.getCantidad()));
    }

    public void limpiarFormulario() {
        txtTitulo.setText("");
        txtAutor.setText("");
        comboEditorial.setSelectedIndex(0);
        comboCategoria.setSelectedIndex(0);
        txtCantidad.setText("");
        tabla.clearSelection();
    }

    public void habilitarEdicion(boolean hayLibroSeleccionado) {
        btnRegistrar.setEnabled(!hayLibroSeleccionado);
        btnModificar.setEnabled(hayLibroSeleccionado);
        btnEliminar.setEnabled(hayLibroSeleccionado);
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
