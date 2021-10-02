package es.joseluisgs.dam.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.service.CommentService;

import java.sql.SQLException;
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
    public String getAllCommentsJSON() {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(commentService.getAllComments());
    }

    public String getCommentByIdJSON(Long id) {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(commentService.getCommentById(id));
    }

    public String postCommentJSON(CommentDTO commentDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(commentService.postComment(commentDTO));
    }

    public String updateCommentJSON(CommentDTO commentDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(commentService.updateComment(commentDTO));
    }

    public String deleteCommentJSON(CommentDTO commentDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(commentService.deleteComment(commentDTO));
    }
}
