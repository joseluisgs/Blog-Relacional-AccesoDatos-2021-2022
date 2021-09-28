package es.joseluisgs.dam.blog.model;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private Long id;
    private String texto;
    // Lista de post que tiene asociado
    private final Set<Post> posts = new HashSet<>();

}
