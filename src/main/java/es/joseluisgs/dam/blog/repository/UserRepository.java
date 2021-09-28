package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRespository<User, Long> {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
