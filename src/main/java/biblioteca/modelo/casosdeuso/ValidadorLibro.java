package biblioteca.modelo.casosdeuso;

import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Libro;

final class ValidadorLibro {

    private ValidadorLibro() {
    }

    static void validar(Libro libro) throws DatosInvalidosException {
        validarObligatorio(libro.getTitulo(), "Título");
        validarObligatorio(libro.getAutor(), "Autor");
        if (libro.getCantidad() < 0) {
            throw new DatosInvalidosException("La cantidad de ejemplares no puede ser negativa.");
        }
    }

    private static void validarObligatorio(String valor, String campo)
            throws DatosInvalidosException {
        if (valor == null || valor.isBlank()) {
            throw new DatosInvalidosException("El campo " + campo + " es obligatorio.");
        }
    }
}

