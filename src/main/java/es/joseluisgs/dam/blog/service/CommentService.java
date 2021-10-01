package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.repository.CommentRepository;

import java.sql.SQLException;
import java.util.List;


public class CommentService extends BaseService<Comment, Long, CommentRepository> {

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CommentService(CommentRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Comment> getAllComments() throws SQLException {
        return this.findAll();
    }

    public Comment createComment(Comment comment) {
        return this.save(comment);
    }
}
