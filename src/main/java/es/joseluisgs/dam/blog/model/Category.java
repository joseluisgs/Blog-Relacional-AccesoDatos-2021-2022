package es.joseluisgs.dam.blog.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
