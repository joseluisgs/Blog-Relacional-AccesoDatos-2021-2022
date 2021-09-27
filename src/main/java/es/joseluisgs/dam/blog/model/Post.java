package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Post {
    private long id;
    private String titulo;
    private String url;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    // Autor
    private User autor;
    // Lista de comentarios asociados
    private List<Comment> comentarios = new ArrayList<>();
    // Categoría a la que pertenece
    private Category categoria;
}
