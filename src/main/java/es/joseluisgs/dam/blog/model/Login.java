package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Login {
    private Long user_id;
    private LocalDateTime ultimoAcceso;
    private String token;

    public Login(Long userId, LocalDateTime ultimoAcceso) {
        this.user_id = userId;
        this.ultimoAcceso = ultimoAcceso;
    }
}
