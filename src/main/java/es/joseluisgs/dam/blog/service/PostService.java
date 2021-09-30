package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;

public class PostService extends BaseService<Post, Long, PostRepository> {

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public PostService(PostRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Post> getAllPosts() throws SQLException {
        return this.findAll();
    }

    public Post createPost(Post post) {
        return this.save(post);
    }
}
