package es.joseluisgs.dam.blog.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.model.Post;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String texto;

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
         return prettyGson.toJson(this);
    }

    public static CategoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return  gson.fromJson(json, CategoryDTO.class);
    }
}
