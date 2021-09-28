package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.repository.PostRepository;

import java.util.List;

public class PostService extends BaseService<Post, Long, PostRepository> {
    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Post> getAllPosts() {
        return this.findAll();
    }

    public Post createPost(Post post) {
        return this.save(post);
    }
}
