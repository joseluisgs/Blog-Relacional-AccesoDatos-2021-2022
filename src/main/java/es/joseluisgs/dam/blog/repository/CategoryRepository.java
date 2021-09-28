package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.model.Category;

import java.util.List;
import java.util.Optional;

public class CategoryRepository implements CrudRespository<Category, Long> {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> getById(Long ID) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

}
