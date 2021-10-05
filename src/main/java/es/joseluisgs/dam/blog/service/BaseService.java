package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.repository.CrudRespository;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor // Requerimos un constructor con al menos las propiedades finales
public abstract class BaseService<T, ID, R extends CrudRespository<T, ID>> {
    protected final R repository;

    // Operaciones CRUD

    // Obtiene todos
    public List<T> findAll() throws SQLException {
        return repository.findAll();
    }

    // Obtiene por ID
    public T getById(ID id) throws SQLException {
        return repository.getById(id);
    }

    // Salva
    public T save(T t) throws SQLException {
        return repository.save(t);
    }

    // Actualiza
    public T update(T t) throws SQLException {
        return repository.update(t);
    }

    // Elimina
    public T delete(T t) throws SQLException {
        return repository.delete(t);
    }
}
