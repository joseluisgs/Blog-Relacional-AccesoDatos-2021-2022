package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository implements CrudRespository<Post, Long> {
    @Override
    public List<Post> findAll() throws SQLException {
        String query = "SELECT * FROM post";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error PostRepository al consultar registros de post"));
        ArrayList<Post> list = new ArrayList<Post>();
        while (result.next()) {
            list.add(
                    new Post(
                            result.getLong("id"),
                            result.getString("titulo"),
                            result.getString("url"),
                            result.getString("contenido"),
                            result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                            result.getLong("user_id"),
                            result.getLong("category_id")
                    )
            );
        }
        db.close();
        return list;
    }

    @Override
    public Post getById(Long ID) throws SQLException {
        String query = "SELECT * FROM post WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error PostRepository al consultar post con ID " + ID));
        if (result.first()) {
            Post post = new Post(
                    result.getLong("id"),
                    result.getString("titulo"),
                    result.getString("url"),
                    result.getString("contenido"),
                    result.getTimestamp("fecha_publicacion").toLocalDateTime(),
                    result.getLong("user_id"),
                    result.getLong("category_id")
            );
            db.close();
            return post;
        } else
            throw new SQLException("Error PostRepository no existe Post con ID: " + ID);
    }

    @Override
    public Post save(Post post) throws SQLException {
        String query = "INSERT INTO post VALUES (null, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, post.getTitulo(), post.getUrl(), post.getContenido(),
                post.getFechaPublicacion(), post.getUser_id(),
                post.getCategory_id()).orElseThrow(() -> new SQLException("Error PostRepository al insertar Post"));
        // Para obtener su ID
        if (res.first()) {
            post.setId(res.getLong(1));
            // una vez insertado comprobamos que está correcto para devolverlo
            db.close();
            return post;
        } else
            throw new SQLException("Error PostRepository al insertar post en BD");
    }

    @Override
    public Post update(Post post) throws SQLException {
        String query = "UPDATE post SET titulo = ?, url = ?, contenido = ?, fecha_publicacion = ?,  " +
                "user_id = ?, category_id = ? WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, post.getTitulo(), post.getUrl(), post.getContenido(),
                post.getFechaPublicacion(), post.getUser_id(), post.getCategory_id(), post.getId());
        db.close();
        if (res > 0)
            return post;
        else
            throw new SQLException("Error PostRepository al actualizar post con id: " + post.getId());
    }

    @Override
    public Post delete(Post post) throws SQLException {
        String query = "DELETE FROM post WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, post.getId());
        db.close();
        if (res > 0)
            return post;
        else
            throw new SQLException("Error PostRepository al eliminar post con id: " + post.getId());
    }
}
