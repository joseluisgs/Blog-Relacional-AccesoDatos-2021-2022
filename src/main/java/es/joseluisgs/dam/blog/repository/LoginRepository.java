package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.model.Login;

import java.util.List;
import java.util.Optional;

public class LoginRepository implements CrudRespository<Login, Long> {
    @Override
    public List<Login> findAll() {
        return null;
    }

    @Override
    public Optional<Login> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Login save(Login login) {
        return null;
    }

    @Override
    public Login update(Login login) {
        return null;
    }

    @Override
    public void delete(Login login) {

    }
}
