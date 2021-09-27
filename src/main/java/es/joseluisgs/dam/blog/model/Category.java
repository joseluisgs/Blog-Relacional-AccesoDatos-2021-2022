package es.joseluisgs.dam.blog.model;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private long id;
    private String texto;
    // Lista de post que tiene asociado
    private Set<Post> posts = new HashSet<>();

}
