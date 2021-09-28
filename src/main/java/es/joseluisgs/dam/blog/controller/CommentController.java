package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.service.CommentService;

import java.util.List;

public class CommentController {
    private static CommentController controller = null;

    // Mi Servicio unido al repositorio
    private final CommentService commentService;

    // Implementamos nuestro Singleton para el controlador
    private CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public static CommentController getInstance() {
        if (controller == null) {
            controller = new CommentController(new CommentService(new CommentRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
}
