package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class User {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private LocalDate fechaRegistro;

    public User(Long id, String nombre, String email, String password, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.password = password;
    }
}
