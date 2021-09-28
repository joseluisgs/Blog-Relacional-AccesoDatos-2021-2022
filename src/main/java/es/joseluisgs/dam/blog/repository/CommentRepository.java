package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.model.Comment;

import java.util.List;
import java.util.Optional;

public class CommentRepository implements RepositoryCRUD<Comment, Long>{
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> getById(Long aLong) {
        return Optional.empty();
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
    public void delete(Comment comment) {

    }
}
