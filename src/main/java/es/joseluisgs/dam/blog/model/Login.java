package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Login {
    private Long user_id;
    private boolean activo;
    private LocalDateTime ultimoAcceso;
}
