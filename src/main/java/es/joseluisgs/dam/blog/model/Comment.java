package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Comment {
    private Long id;
    private String texto;
    private LocalDateTime fechaPublicacion;
    // Autor que la realiza
    private Long user_id;
    // Post al que pertenece
    private Long post_id;

    public Comment(Long id, String texto, LocalDateTime fechaPublicacion, Long user_id, Long post_id) {
        this.id = id;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.user_id = user_id;
        this.post_id = post_id;
    }
}
