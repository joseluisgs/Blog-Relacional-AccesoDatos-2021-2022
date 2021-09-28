package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.model.Post;

import java.util.List;
import java.util.Optional;

public class PostRepository implements RepositoryCRUD<Post, Long> {
    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> getById(Long aLong) {
        return Optional.empty();
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
    public void delete(Post post) {

    }
}
