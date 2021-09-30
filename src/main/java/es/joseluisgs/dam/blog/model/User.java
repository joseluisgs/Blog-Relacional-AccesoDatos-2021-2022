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
    private LocalDate fechaRegistro;
    private String password;
    // TODO Bidireccionalidad
    // Lista de Comentarios
    //private Set<Comment> comentarios = new HashSet<>();
    // Lista de Posts
    //private Set<Post> posts = new HashSet<>();
    // Su login activo si lo tiene
    //private Login login;
}
