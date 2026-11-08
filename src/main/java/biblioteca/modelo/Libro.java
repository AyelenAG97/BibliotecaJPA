package biblioteca.modelo;

import jakarta.persistence.*;

/**
 * Entidad que representa un libro del catálogo de la biblioteca y su stock.
 */
@Entity
@Table(name = "libros")
public class Libro {

    public static final String[] EDITORIALES = {"Planeta", "Sudamericana", "Paidós"};
    public static final String[] CATEGORIAS = {"Novela", "Ciencia Ficción", "Técnico"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    private int cantidad;

    public Libro() {
        // JPA necesita este constructor sin argumentos
    }

    public Libro(String titulo, String autor, String editorial,
                 String categoria, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return titulo + " - " + autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro otro)) return false;
        return id != null && id.equals(otro.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

