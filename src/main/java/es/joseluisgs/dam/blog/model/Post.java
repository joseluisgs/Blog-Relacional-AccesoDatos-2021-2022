package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Post {
    private Long id;
    private String titulo;
    private String url;
    private String contenido;
    private LocalDateTime fechaPublicacion;

    // Autor
    private User autor;
    // Categoría a la que pertenece
    private Category categoria;

    // TODO Lista de comentarios asociados
    // private List<Comment> comentarios = new ArrayList<>();


    public Post(Long id, String titulo, String url, String contenido, LocalDateTime fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
    }
}
