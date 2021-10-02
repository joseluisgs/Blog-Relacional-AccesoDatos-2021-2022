package es.joseluisgs.dam.blog.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {
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

    public static UserDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, UserDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}