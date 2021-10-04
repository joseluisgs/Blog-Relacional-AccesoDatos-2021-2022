package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository implements CrudRespository<Post, Long> {
    @Override
    public List<Post> findAll() {
        try {
            String query = "SELECT * FROM post";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query);
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
        } catch (SQLException e) {
            System.err.println("Error findAll: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Post getById(Long ID) {
        try {
            String query = "SELECT * FROM post WHERE id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query, ID);
            result.absolute(1);
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
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Post save(Post post) {
        try {
            String query = "INSERT INTO post VALUES (null, ?, ?, ?, ?, ?, ?)";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet res = db.insert(query, post.getTitulo(), post.getUrl(), post.getContenido(),
                    post.getFechaPublicacion(), post.getUser_id(), post.getCategory_id());
            if (res != null) {
                // Para obtener su ID
                res.absolute(1);
                post.setId(res.getLong(1));
            }
            // una vez insertado comprobamos que esta correcto para devolverlo
            db.close();
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
        } finally {
            return post;
        }
    }

    @Override
    public Post update(Post post) {
        String query = "UPDATE post SET titulo = ?, url = ?, contenido = ?, fecha_publicacion = ?,  " +
                "user_id = ?, category_id = ? WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, post.getTitulo(), post.getUrl(), post.getContenido(),
                post.getFechaPublicacion(), post.getUser_id(), post.getCategory_id(), post.getId());
        db.close();
        if (res != 0)
            return post;
        return null;
    }

    @Override
    public Post delete(Post post) {
        String query = "DELETE FROM post WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, post.getId());
        db.close();
        if (res != 0)
            return post;
        return null;
    }
}
