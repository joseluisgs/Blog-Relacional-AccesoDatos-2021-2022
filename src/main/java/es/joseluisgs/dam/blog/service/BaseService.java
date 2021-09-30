package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.repository.CrudRespository;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // Requerimos un constructor con al menos las propiedades finales
public abstract class BaseService<T, ID, R extends CrudRespository<T, ID>> {
    protected final R repository;

    // Operaciones CRUD

    // Obtiene todos
    public List<T> findAll() {
        return repository.findAll();
    }

    // Obtiene por ID
    public T getById(ID id) {
        return repository.getById(id);
    }

    // Salva
    public T save(T t) {
        return repository.save(t);
    }

    // Actualiza
    public T update(T t) {
        return repository.update(t);
    }

    // Elimina
    public T delete(T t) {
        return repository.delete(t);
    }
}
