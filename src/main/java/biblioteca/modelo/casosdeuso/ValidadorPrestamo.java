package biblioteca.modelo.casosdeuso;

import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Prestamo;

final class ValidadorPrestamo {

    private ValidadorPrestamo() {
    }

    static void validar(Prestamo prestamo) throws DatosInvalidosException {
        if (prestamo.getSocio() == null) {
            throw new DatosInvalidosException("Debés seleccionar un socio.");
        }
        if (prestamo.getLibro() == null) {
            throw new DatosInvalidosException("Debés seleccionar un libro.");
        }
        if (prestamo.getFechaPrestamo() == null) {
            throw new DatosInvalidosException("La fecha de préstamo es obligatoria.");
        }
        if (prestamo.getFechaDevolucion() != null
                && prestamo.getFechaDevolucion().isBefore(prestamo.getFechaPrestamo())) {
            throw new DatosInvalidosException(
                    "La fecha de devolución no puede ser anterior a la fecha de préstamo.");
        }
    }
}

