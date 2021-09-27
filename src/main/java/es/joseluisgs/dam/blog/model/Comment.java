package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private long id;
    private String texto;
    // Autor que la realiza
    private User autor;
    // Post al que pertenece
    private Post post;
    private LocalDateTime fechaPublicacion;
}
