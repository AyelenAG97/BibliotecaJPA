/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.vista;

/**
 *
 * @author Ayelén
 */

import biblioteca.modelo.Socio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SocioTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = {
        "ID", "DNI", "Apellido", "Nombre", "Email", "Teléfono", "Estado"};

    private List<Socio> socios = new ArrayList<>();

    public void actualizar(List<Socio> nuevosSocios) {
        this.socios = nuevosSocios;
        fireTableDataChanged();
    }

    public Socio getSocio(int fila) {
        return socios.get(fila);
    }

    @Override
    public int getRowCount() {
        return socios.size();
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
        Socio socio = socios.get(fila);
        return switch (columna) {
            case 0 -> socio.getId();
            case 1 -> socio.getDni();
            case 2 -> socio.getApellido();
            case 3 -> socio.getNombre();
            case 4 -> socio.getEmail();
            case 5 -> socio.getTelefono();
            case 6 -> socio.getEstado();
            default -> null;
        };
    }
}
