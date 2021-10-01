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
            ResultSet result = db.query(query);
            ArrayList<Post> list = new ArrayList<Post>();
            while (true) {
                if (!result.next()) break;
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
            String query = "SELECT * FROM post WHERE id = " + ID;
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
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
        String query = "INSERT INTO post (titulo, url, contenido, fecha_publicacion, user_id, category_id) VALUES (" +
                "'" + post.getTitulo() + "', " +
                "'" + post.getUrl() + "', " +
                "'" + post.getContenido() + "', " +
                "'" + post.getFechaPublicacion() + "', " +
                post.getUser_id() + ", " +
                post.getCategory_id() +
                ")";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        if (res != 0)
            // Para obtener su ID
            post = this.getItem(post);
        // una vez insertado comprobamos que esta correcto para devolverlo
        db.close();
        return post;
    }

    @Override
    public Post update(Post post) {
        String query = "UPDATE post SET " +
                "titulo = '" + post.getTitulo() + "', " +
                "contenido = '" + post.getContenido() + "', " +
                "url = '" + post.getUrl() + "', " +
                "fecha_publicacion = '" + post.getFechaPublicacion() + "', " +
                "user_id = " + post.getUser_id() + ", " +
                "category_id = " + post.getCategory_id() + " " +
                " WHERE id = " + post.getId();

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return post;
        return null;
    }

    @Override
    public Post delete(Post post) {
        String query = "DELETE FROM post " +
                "WHERE id = " + post.getId();

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return post;
        return null;
    }

    private Post getItem(Post post) {
        try {
            String query = "SELECT * FROM post WHERE url = '" + post.getUrl() + "'";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            result.absolute(1);
            post = new Post(
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
            System.err.println("Error getItem: " + e.getMessage());
            return null;
        }
    }
}
