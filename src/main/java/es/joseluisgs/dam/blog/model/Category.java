package es.joseluisgs.dam.blog.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Category {
    private Long id;
    private String texto;
    // Lista de post que tiene asociado
    private final Set<Post> posts = new HashSet<>();

}
