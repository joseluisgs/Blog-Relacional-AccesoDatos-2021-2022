package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Login {
    private User user;
    private boolean activo;
    private LocalDateTime ultimoAcceso;
}
