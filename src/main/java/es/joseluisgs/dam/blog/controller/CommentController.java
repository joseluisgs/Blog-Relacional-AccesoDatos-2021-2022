package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.service.CommentService;

import java.sql.SQLException;

public class CommentController {
    private static CommentController controller = null;

    // Mi Servicio unido al repositorio
    private final CommentService commentService;
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
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(commentService.getAllComments());
        } catch (SQLException e) {
            System.err.println("Error CommentController en getAllComments: " + e.getMessage());
            return "Error CommentController en getAllComments: " + e.getMessage();
        }
    }

    public String getCommentByIdJSON(Long id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(commentService.getCommentById(id));
        } catch (SQLException e) {
            System.err.println("Error CommentController en getCommentById: " + e.getMessage());
            return "Error CommentController en getCommentById: " + e.getMessage();
        }
    }

    public String postCommentJSON(CommentDTO commentDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(commentService.postComment(commentDTO));
        } catch (SQLException e) {
            System.err.println("Error CommentController en postComment: " + e.getMessage());
            return "Error CommentController en postComment: " + e.getMessage();
        }
    }

    public String updateCommentJSON(CommentDTO commentDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(commentService.updateComment(commentDTO));
        } catch (SQLException e) {
            System.err.println("Error CommentController en updateCommment: " + e.getMessage());
            return "Error CommentController en updateComment: " + e.getMessage();
        }
    }

    public String deleteCommentJSON(CommentDTO commentDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(commentService.deleteComment(commentDTO));
        } catch (SQLException e) {
            System.err.println("Error CommentController en deleteComment: " + e.getMessage());
            return "Error CommentController en deleteComment: " + e.getMessage();
        }
    }

}
