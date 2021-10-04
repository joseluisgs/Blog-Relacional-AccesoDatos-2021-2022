package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentRepository implements CrudRespository<Comment, Long> {
    @Override
    public List<Comment> findAll() {
        try {
            String query = "SELECT * FROM comment";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query);
            ArrayList<Comment> list = new ArrayList<Comment>();
            while (result.next()) {
                list.add(
                        new Comment(
                                result.getLong("id"),
                                result.getString("texto"),
                                result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                                result.getLong("user_id"),
                                result.getLong("post_id")
                        )
                );
            }
            db.close();
            return list;
        } catch (SQLException e) {
            System.err.println("Error findAll: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Comment getById(Long ID) {
        try {
            String query = "SELECT * FROM comment WHERE id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query, ID);
            result.absolute(1);
            Comment comment = new Comment(
                    result.getLong("id"),
                    result.getString("texto"),
                    result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                    result.getLong("user_id"),
                    result.getLong("post_id")
            );
            db.close();
            return comment;
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Comment save(Comment comment) {
        try {
        // Le dotamos de un UUID único, por si hay dos mensajes iguales en texto y momento.
        // también podríamos usar un hash, pero podrían llegar dos mensajes iguales en el mismo instante
        // es imporbable, pero así explico el uuid
        UUID uuid = UUID.randomUUID();
        String query = "INSERT INTO comment VALUES (null, ?, ?, ?, ?, ?)";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, comment.getTexto(), comment.getFechaPublicacion(), uuid,
                comment.getUser_id(), comment.getPost_id());
            if (res != null) {
                // Para obtener su ID
                res.absolute(1);
                comment.setId(res.getLong(1));
            }
        // una vez insertado comprobamos que esta correcto para devolverlo
        db.close();
        } finally {
            return comment;
        }
    }

    @Override
    public Comment update(Comment comment) {
        String query = "UPDATE comment SET texto = ?, fecha_publicacion = ?, user_id = ?, post_id = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, comment.getTexto(), comment.getFechaPublicacion(), comment.getUser_id(),
                comment.getPost_id(), comment.getId());
        db.close();
        if (res != 0)
            return comment;
        return null;
    }

    @Override
    public Comment delete(Comment comment) {
        String query = "DELETE FROM comment WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, comment.getId());
        db.close();
        if (res != 0)
            return comment;
        return null;
    }

    public List<Comment> getByPost(Long idPost) {
        try {
            String query = "SELECT * FROM comment where post_id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query, idPost);
            ArrayList<Comment> list = new ArrayList<Comment>();
            while (result.next()) {
                list.add(
                        new Comment(
                                result.getLong("id"),
                                result.getString("texto"),
                                result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                                result.getLong("user_id"),
                                result.getLong("post_id")
                        )
                );
            }
            db.close();
            return list;
        } catch (SQLException e) {
            System.err.println("Error findAll: " + e.getMessage());
            return null;
        }
    }
}
