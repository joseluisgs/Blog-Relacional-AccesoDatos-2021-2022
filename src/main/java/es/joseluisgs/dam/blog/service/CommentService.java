package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.repository.CommentRepository;

import java.util.List;

public class CommentService extends BaseService<Comment, Long, CommentRepository> {
    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Comment> getAllComments() {
        return this.findAll();
    }

    public Comment createComment(Comment comment) {
        return this.save(comment);
    }
}
