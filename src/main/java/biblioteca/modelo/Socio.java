package biblioteca.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa a un socio registrado en la biblioteca.
 */
@Entity
@Table(name = "socios")
public class Socio {

    public static final String[] ESTADOS = {"Activo", "Suspendido", "Inactivo"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
    private String estado;

    public Socio() {
        // JPA necesita este constructor sin argumentos
    }

    public Socio(String nombre, String apellido, String dni,
                 String email, String telefono, String estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni; 
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return apellido + ", " + nombre + " (DNI: " + dni + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Socio otro)) return false;
        return id != null && id.equals(otro.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}