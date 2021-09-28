package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.service.PostService;

import java.util.List;

public class PostController {
    private static PostController controller = null;

    // Mi Servicio unido al repositorio
    private final PostService postService;

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
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }
}