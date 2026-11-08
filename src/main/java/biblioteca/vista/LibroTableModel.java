package biblioteca.vista;

import biblioteca.modelo.Libro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LibroTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = {
        "ID", "Título", "Autor", "Editorial", "Categoría", "Cantidad"};

    private List<Libro> libros = new ArrayList<>();

    public void actualizar(List<Libro> nuevosLibros) {
        this.libros = nuevosLibros;
        fireTableDataChanged();
    }

    public Libro getLibro(int fila) {
        return libros.get(fila);
    }

    @Override
    public int getRowCount() {
        return libros.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int columna) {
        return COLUMNAS[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Libro libro = libros.get(fila);
        return switch (columna) {
            case 0 -> libro.getId();
            case 1 -> libro.getTitulo();
            case 2 -> libro.getAutor();
            case 3 -> libro.getEditorial();
            case 4 -> libro.getCategoria();
            case 5 -> libro.getCantidad();
            default -> null;
        };
    }
}

