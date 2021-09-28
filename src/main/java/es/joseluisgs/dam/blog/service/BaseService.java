package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.repository.CrudRespository;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID, R extends CrudRespository<T, ID>> {
    protected R repository;

    // Operaciones CRUD

    // Obtiene todos
    public List<T> findAll() {
        return repository.findAll();
    }

    // Obtiene por ID
    public Optional<T> getById(ID id) {
        return repository.getById(id);
    }

    // Salva
    public T save(T t) {
        return repository.save(t);
    }

    // Actualiza
    public T update(T t) {
        return repository.save(t);
    }

    // Elimina
    public void delete(T t) {
        repository.delete(t);
    }
}
