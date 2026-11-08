package biblioteca.vista;

import biblioteca.modelo.Prestamo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrestamoTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = {
        "ID", "Socio", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado"};

    private List<Prestamo> prestamos = new ArrayList<>();

    public void actualizar(List<Prestamo> nuevosPrestamos) {
        this.prestamos = nuevosPrestamos;
        fireTableDataChanged();
    }

    public Prestamo getPrestamo(int fila) {
        return prestamos.get(fila);
    }

    @Override
    public int getRowCount() {
        return prestamos.size();
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
        Prestamo prestamo = prestamos.get(fila);
        return switch (columna) {
            case 0 -> prestamo.getId();
            case 1 -> prestamo.getSocio();
            case 2 -> prestamo.getLibro();
            case 3 -> prestamo.getFechaPrestamo();
            case 4 -> prestamo.getFechaDevolucion();
            case 5 -> prestamo.getEstado();
            default -> null;
        };
    }
}

