package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository implements CrudRespository<Post, Long> {
    @Override
    public List<Post> findAll() {
        try {
            String query = "SELECT * FROM post";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            ArrayList<Post> list = new ArrayList<Post>();
            while(true) {
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
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public Post delete(Post post) {
        return null;
    }
}
