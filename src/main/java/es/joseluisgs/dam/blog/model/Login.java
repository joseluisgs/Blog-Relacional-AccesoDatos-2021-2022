package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Login {
    private Long user_id;
    private LocalDateTime ultimoAcceso;
    private String token;

    public Login(Long userId, LocalDateTime ultimoAcceso, String token) {
        this.user_id = userId;
        this.ultimoAcceso = ultimoAcceso;
        this.token = token;
    }
}
