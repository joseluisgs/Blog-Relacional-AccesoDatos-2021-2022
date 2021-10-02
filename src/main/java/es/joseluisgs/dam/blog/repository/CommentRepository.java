package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements CrudRespository<Comment, Long> {
    @Override
    public List<Comment> findAll() {
        try {
            String query = "SELECT * FROM comment";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            ArrayList<Comment> list = new ArrayList<Comment>();
            while (true) {
                if (!result.next()) break;
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
            String query = "SELECT * FROM comment WHERE id = " + ID;
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
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
        String query = "INSERT INTO comment (texto, fecha_publicacion, user_id, post_id) VALUES (" +
                "'" + comment.getTexto() + "', " +
                "'" + comment.getFechaPublicacion() + "', " +
                comment.getUser_id() + ", " +
                comment.getPost_id() +
                ")";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        if (res != 0)
            // Para obtener su ID
            comment = this.getItem(comment);
        // una vez insertado comprobamos que esta correcto para devolverlo
        db.close();
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        String query = "UPDATE comment SET " +
                "texto = '" + comment.getTexto() + "', " +
                "fecha_publicacion = '" + comment.getFechaPublicacion() + "', " +
                "user_id = " + comment.getUser_id() + ", " +
                "post_id = " + comment.getPost_id() + " " +
                " WHERE id = " + comment.getId();

        System.out.println(query);
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return comment;
        return null;
    }

    @Override
    public Comment delete(Comment comment) {
        String query = "DELETE FROM comment " +
                "WHERE id = " + comment.getId();

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return comment;
        return null;
    }

    // TODO implementar un hash para identificar los campos sin recurrir a un campo que puede estar repetido
    private Comment getItem(Comment comment) {
        try {
            String query = "SELECT * FROM comment WHERE texto = '" + comment.getTexto() + "'";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            result.absolute(1);
            comment = new Comment(
                    result.getLong("id"),
                    result.getString("texto"),
                    result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                    result.getLong("user_id"),
                    result.getLong("post_id")
            );
            db.close();
            return comment;
        } catch (SQLException e) {
            System.err.println("Error getItem: " + e.getMessage());
            return null;
        }
    }
}
