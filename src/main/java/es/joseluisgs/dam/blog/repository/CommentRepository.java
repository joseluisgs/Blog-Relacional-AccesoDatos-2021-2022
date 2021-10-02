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
    public Comment getById(Long aLong) {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public Comment delete(Comment comment) {
        return null;
    }
}
