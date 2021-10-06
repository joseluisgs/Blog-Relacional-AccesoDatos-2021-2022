package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.service.PostService;

import java.sql.SQLException;

public class PostController {
    private static PostController controller = null;

    // Mi Servicio unido al repositorio
    private final PostService postService;
    // Eliminamos los campso que qno queremos que salgan en el JSON
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password")
                    || field.getName().startsWith("user_id")
                    || field.getName().startsWith("category_id");
        }
    };

    // Implementamos nuestro Singleton para el controlador
    private PostController(PostService postService) {
        this.postService = postService;
    }

    public static PostController getInstance() {
        if (controller == null) {
            controller = new PostController(new PostService(new PostRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public String getAllPostJSON() {
        // Vamos a devolver el JSON de las categorías
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(postService.getAllPosts());
        } catch (SQLException e) {
            System.err.println("Error PostController en getAllPots: " + e.getMessage());
            return "Error PostController en getAllPost: " + e.getMessage();
        }
    }

    public String getPostByIdJSON(Long id) {
        // Vamos a devolver el JSON de las categorías
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(postService.getPostById(id));
        } catch (SQLException e) {
            System.err.println("Error PostController en getPostById " + e.getMessage());
            return "Error PostController en getPostById: " + e.getMessage();
        }
    }

    public String postPostJSON(PostDTO postDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(postService.postPost(postDTO));
        } catch (SQLException e) {
            System.err.println("Error PostController en postPost: " + e.getMessage());
            return "Error PostController en postPost: " + e.getMessage();
        }
    }

    public String updatePostJSON(PostDTO postDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(postService.updatePost(postDTO));
        } catch (SQLException e) {
            System.err.println("Error PostController en updatePost: " + e.getMessage());
            return "Error PostController en updatePost: " + e.getMessage();
        }
    }

    public String deletePostJSON(PostDTO postDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(postService.deletePost(postDTO));
        } catch (SQLException e) {
            System.err.println("Error PostController en deletePost: " + e.getMessage());
            return "Error PostController en deletePost: " + e.getMessage();
        }
    }
}