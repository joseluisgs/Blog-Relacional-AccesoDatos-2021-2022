package es.joseluisgs.dam.blog.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PostDTO {
    private Long id;
    private String titulo;
    private String url;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    // Autor
    private User user;
    // Categoría a la que pertenece
    private Category category;

    // Para mejorar las relaciones y como es un dTO vamos a poner los ids
    private Long user_id, category_id;

    // TODO Lista de comentarios asociados
    private List<Comment> comments;

    /*public static CategoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CategoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }*/
}
