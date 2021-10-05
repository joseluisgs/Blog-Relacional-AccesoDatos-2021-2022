package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CommentRepository implements CrudRespository<Comment, Long> {
    @Override
    public List<Comment> findAll() throws SQLException {
            String query = "SELECT * FROM comment";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error CommentRepository al consultar registros de comentarios"));
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

    }

    @Override
    public Comment getById(Long ID) throws SQLException {
        String query = "SELECT * FROM comment WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error CommentRepository al consultar comentario con ID " + ID));
        if (result.first()) {
            Comment comment = new Comment(
                    result.getLong("id"),
                    result.getString("texto"),
                    result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                    result.getLong("user_id"),
                    result.getLong("post_id")
            );
            db.close();
            return comment;
        } else
            throw new SQLException("Error CommentRepository no existe comentario con ID: " + ID);
    }

    @Override
    public Comment save(Comment comment) throws SQLException {
            // Le dotamos de un UUID único, por si hay dos mensajes iguales en texto y momento.
            // también podríamos usar un hash, pero podrían llegar dos mensajes iguales en el mismo instante
            // es imporbable, pero así explico el uuid
            UUID uuid = UUID.randomUUID();
            String query = "INSERT INTO comment VALUES (null, ?, ?, ?, ?, ?)";

            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet res = db.insert(query, comment.getTexto(), comment.getFechaPublicacion(),
                    comment.getUser_id(), comment.getPost_id(),
                    uuid.toString()).orElseThrow(() -> new SQLException("Error CommentRepository al insertar Commentario"));
                // Para obtener su ID
                if(res.first()) {
                    comment.setId(res.getLong(1));
                    // una vez insertado comprobamos que esta correcto para devolverlo
                    db.close();
                    return comment;
                }else
                    throw new SQLException("Error CommentRepository al insertar comentario en BD");
    }

    @Override
    public Comment update(Comment comment) throws SQLException {
        String query = "UPDATE comment SET texto = ?, fecha_publicacion = ?, user_id = ?, post_id = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, comment.getTexto(), comment.getFechaPublicacion(), comment.getUser_id(),
                comment.getPost_id(), comment.getId());
        db.close();
        if (res > 0)
            return comment;
        else
            throw new SQLException("Error CommentRepository al actualizar comentario con id: " + comment.getId());
    }

    @Override
    public Comment delete(Comment comment) throws SQLException {
        String query = "DELETE FROM comment WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, comment.getId());
        db.close();
        if (res > 0)
            return comment;
        else
            throw new SQLException("Error CommentRepository al eliminar comentario con id: " + comment.getId());
    }

    public List<Comment> getByPost(Long idPost) throws SQLException {
            String query = "SELECT * FROM comment where post_id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
        ResultSet result = db.select(query, idPost).orElseThrow(() -> new SQLException("Error CommentRepository no existe comentario con idPost " + idPost));
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
    }
}
