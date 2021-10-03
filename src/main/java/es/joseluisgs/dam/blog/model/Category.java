package es.joseluisgs.dam.blog.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Long id;
    private String texto;


    public Category(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

}
