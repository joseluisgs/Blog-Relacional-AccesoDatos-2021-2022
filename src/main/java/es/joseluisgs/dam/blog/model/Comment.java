package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private String texto;
    private LocalDateTime fechaPublicacion;
    // Autor que la realiza
    private Long user_id;
    // Post al que pertenece
    private Long post_id;
}
