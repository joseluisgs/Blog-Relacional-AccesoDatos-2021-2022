package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.service.PostService;

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
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(postService.getAllPosts());
    }

    public String getPostByIdJSON(Long id) {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(postService.getPostById(id));
    }

    public String postPostJSON(PostDTO postDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(postService.postPost(postDTO));
    }

    public String updatePostJSON(PostDTO postDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(postService.updatePost(postDTO));
    }

    public String deletePostJSON(PostDTO postDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(postService.deletePost(postDTO));
    }
}