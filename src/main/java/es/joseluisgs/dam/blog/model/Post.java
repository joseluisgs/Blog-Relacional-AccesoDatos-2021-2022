package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Post {
    private Long id;
    private String titulo;
    private String url;
    private String contenido;
    private LocalDateTime fechaPublicacion;

    // Autor
    private Long user_id;
    // Categoría a la que pertenece
    private Long category_id;


    public Post(Long id, String titulo, String url, String contenido, LocalDateTime fechaPublicacion, Long user_id, Long category_id) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.user_id = user_id;
        this.category_id = category_id;
    }
}
