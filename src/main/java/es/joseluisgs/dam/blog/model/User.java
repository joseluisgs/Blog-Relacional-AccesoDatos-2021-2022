package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private long id;
    private String nombre;
    private String email;
    private LocalDate fechaRegistro;
    private String password;
    // Lista de Comentarios
    private Set<Comment> comentarios = new HashSet<>();
    // Lista de Posts
    private Set<Post> posts = new HashSet<>();
    // Su login activo si lo tiene
    private Login login;


}
