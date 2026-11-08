package biblioteca.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que vincula un socio con un libro prestado y sus fechas asociadas.
 */
@Entity
@Table(name = "prestamos")
public class Prestamo {

    public static final String[] ESTADOS = {"Activo", "Devuelto"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;

    public Prestamo() {
        // JPA necesita este constructor sin argumentos
    }

    public Prestamo(Socio socio, Libro libro, LocalDate fechaPrestamo,
                    LocalDate fechaDevolucion, String estado) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

