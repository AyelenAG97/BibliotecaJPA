/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.modelo.casosdeuso;

/**
 *
 * @author Ayelén
 */

import biblioteca.modelo.DatosInvalidosException;
import biblioteca.modelo.Socio;

final class ValidadorSocio {

    private ValidadorSocio() {
     
    }

    static void validar(Socio socio) throws DatosInvalidosException {
        validarObligatorio(socio.getNombre(), "Nombre");
        validarObligatorio(socio.getApellido(), "Apellido");
        validarObligatorio(socio.getDni(), "DNI");
        validarDni(socio.getDni());
    }

    private static void validarObligatorio(String valor, String campo)
            throws DatosInvalidosException {
        if (valor == null || valor.isBlank()) {
            throw new DatosInvalidosException("El campo " + campo + " es obligatorio.");
        }
    }

    private static void validarDni(String dni) throws DatosInvalidosException {
        boolean soloNumeros = dni.chars().allMatch(Character::isDigit);
        if (!soloNumeros || dni.length() < 7 || dni.length() > 8) {
            throw new DatosInvalidosException(
                    "El DNI debe tener 7 u 8 números, sin puntos.");
        }
    }
}
