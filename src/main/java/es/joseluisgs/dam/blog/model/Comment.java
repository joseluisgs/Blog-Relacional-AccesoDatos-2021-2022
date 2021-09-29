package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private String texto;
    // Autor que la realiza
    private User user;
    // Post al que pertenece
    private Post post;
    private LocalDateTime fechaPublicacion;
}
